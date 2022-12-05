package com.betastudio.BetaStudioServer.RequestMgmtBE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RequestDataMgmtIF {

    @PostMapping("/api/v1/requests/save/")
    public Request save(@RequestBody Request request);

    @GetMapping("/api/v1/requests/getbyUsername")
    public List<Request> getbyUsername(@RequestParam(name = "username") String username);

    @GetMapping("/api/v1/requests/getbyMedID")
    public List<Request> getbyMedID(@RequestParam(name = "medID") String medID);

    @GetMapping("/api/v1/requests/getbyTimestamp")
    public Request getbyTimestamp(@RequestParam(name = "time") String time);

    @GetMapping("/api/v1/requests/delete")
    public String delete(@RequestParam(name = "time") String time);

}
