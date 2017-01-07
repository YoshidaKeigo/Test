package link.revie.service.category;

import java.util.List;

import link.revie.model.entity.Category;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

	List<Category> findAll();

}
