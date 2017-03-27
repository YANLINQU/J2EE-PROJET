package friendsofmine.repositories;

import friendsofmine.domain.Utilisateur;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Created by QYL on 2017/3/6.
 */
public interface MembreRepository extends PagingAndSortingRepository<Utilisateur,Long>,
        QueryByExampleExecutor<Utilisateur>{
}
