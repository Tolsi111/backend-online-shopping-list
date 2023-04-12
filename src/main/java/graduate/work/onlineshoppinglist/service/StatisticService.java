package graduate.work.onlineshoppinglist.service;

import org.springframework.stereotype.Service;

import java.util.List;

import graduate.work.onlineshoppinglist.model.Statistic;
import graduate.work.onlineshoppinglist.repository.StatisticRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticService {

    private StatisticRepository statisticRepository;

    public List<Statistic> getStatistics() {
        return statisticRepository.findAll();
    }

}
