package pl.mateusz.kalksztejn.STM.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mateusz.kalksztejn.STM.models.Task;
import pl.mateusz.kalksztejn.STM.models.enums.Status;
import pl.mateusz.kalksztejn.STM.models.enums.Type;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = " select * from task  where title = ?1 or type = :#{#type?.name()} or status = :#{#status?.name()}  ", nativeQuery = true)
    List<Task> findByTitleOrStatusOrType(String title, Type type, Status status);


    @Query(value = " select * from task where task_id = ?1", nativeQuery = true)
    Task findByTaskId(int id);

    @Query("SELECT t FROM Task t WHERE t.user.userId = :userId")
    List<Task> findTasksByUserId(@Param("userId") Integer userId);

}
