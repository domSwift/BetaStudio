package com.betastudio.BetaStudioServer.RequestMgmtBE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RequestController implements RequestDataMgmtIF {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/api/v1/requests/save/")
    public Request save(@RequestBody Request request){
        return requestService.saveNew(request);
    }

    @GetMapping("/api/v1/requests/getbyUsername")
    public List<Request> getbyUsername(@RequestParam(name = "username") String username){
        return requestService.getbyUsername(username);
    }

    @GetMapping("/api/v1/requests/getbyMedID")
    public List<Request> getbyMedID(@RequestParam(name = "medID") String medID){
        return requestService.getbyMedID(medID);
    }

    @GetMapping("/api/v1/requests/getbyTimestamp")
    public Request getbyTimestamp(@RequestParam(name = "time") String time){
        return requestService.getbyTimestamp(time);
    }

    @GetMapping("/api/v1/requests/delete")
    public String delete(@RequestParam(name = "time") String time){
        return requestService.delete(time);
    }

}
