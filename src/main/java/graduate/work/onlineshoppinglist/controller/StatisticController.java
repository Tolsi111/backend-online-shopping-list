package graduate.work.onlineshoppinglist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graduate.work.onlineshoppinglist.exceptions.error_handling.response.Response;
import graduate.work.onlineshoppinglist.model.Statistic;
import graduate.work.onlineshoppinglist.model.responses.DataResponse;
import graduate.work.onlineshoppinglist.service.StatisticService;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping
    public Response<Statistic> getStatistics() {
        return new Response<>(new DataResponse<>((statisticService.getStatistics())));
    }
}
