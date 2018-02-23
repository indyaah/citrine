package pro.anuj.citrine.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import pro.anuj.citrine.domain.Material;

import java.util.List;

@Mapper
@Component
public interface MaterialMapper {

    List<Material> search(FormulaQuery params);

    int save(Material material);
}
