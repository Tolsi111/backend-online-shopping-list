package graduate.work.onlineshoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.work.onlineshoppinglist.model.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic,Long> {
}
