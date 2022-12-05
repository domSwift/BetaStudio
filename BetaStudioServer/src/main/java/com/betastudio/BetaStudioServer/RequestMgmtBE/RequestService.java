package com.betastudio.BetaStudioServer.RequestMgmtBE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request saveNew(Request request){
        System.out.println(request);
        return requestRepository.save(request);
    }

    public List<Request> getbyUsername(String username){
        List<Request> reqs = new ArrayList<>();
        for (Request request : Streamable.of(requestRepository.findAll())) {
            if(request.getUsername().equals(username)){
                reqs.add(request);
            }
        }
        return reqs;
    }

    public List<Request> getbyMedID(String medID){
        List<Request> reqs = new ArrayList<>();
        for (Request request : Streamable.of(requestRepository.findAll())) {
            if(request.getMedRef().equals(medID)){
                reqs.add(request);
            }
        }
        setPriorities(reqs);
        orderByPriority(reqs);
        return reqs;
    }

    public Request getbyTimestamp(String timestr){
       LocalDateTime time =  LocalDateTime.parse(timestr);
       Request res = null;
        for (Request request : Streamable.of(requestRepository.findAll())) {
            if(request.getOraRicezione().isEqual(time)){
                res = request;
            }
        }
       return res;
    }

    public String delete(String timestr){
        String cancellata = "";
        LocalDateTime time =  LocalDateTime.parse(timestr);
        for (Request request : Streamable.of(requestRepository.findAll())) {
            if(request.getOraRicezione().isEqual(time)){
                requestRepository.delete(request);
                cancellata = "cancellata";
            }
        }
        return cancellata;
    }

    public void orderByPriority(List<Request> list){
        boolean ordered = false;
        while(!ordered) {
            ordered = true;
            for(int i= 0; i<list.size()-1; i++) {
                double val1 = list.get(i).getPriority();
                double val2 = list.get(i+1).getPriority();
                if(val1<val2) {
                    Request temp = list.get(i);
                    list.set(i,list.get(i+1));
                    list.set(i+1, temp);
                    ordered = false;
                }
            }
        }
    }

    public void setPriorities(List<Request> list){
        for(Request r: list){
            r.setPriority(calculatePriority(r));
            requestRepository.save(r);
        }
    }

    public double calculatePriority(Request r){
        double cont = Contesto.valueOf(r.getContesto()).getValue();
        double area = AreaSanitaria.valueOf(r.getAreaSanitaria()).getValue();
        long l = timeDiff(r.getOraRicezione().toString());
        double time = (double)l;
        return (cont + area)/100*time;
    }

    public long timeDiff(String date){
        LocalDateTime requestTime = LocalDateTime.parse(date);
        Duration duration = Duration.between(requestTime, LocalDateTime.now());
        return duration.getSeconds()/3600;
    }

}
