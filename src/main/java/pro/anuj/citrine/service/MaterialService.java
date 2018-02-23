package pro.anuj.citrine.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.anuj.citrine.domain.Material;
import pro.anuj.citrine.persistence.Criterion;
import pro.anuj.citrine.persistence.FormulaQuery;
import pro.anuj.citrine.persistence.MaterialMapper;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@Component
public class MaterialService {

    private final MaterialMapper materialMapper;
    private static ImmutableMap<String, Criterion> LOOK_UP_BY_API_VERB =
            Maps.uniqueIndex(Arrays.asList(Criterion.values()), Criterion::getApiVerb);

    @Autowired
    public MaterialService(MaterialMapper materialMapper) {
        this.materialMapper = materialMapper;
    }


    public int save(Material material) {
        return materialMapper.save(material);
    }

    public List<Material> search(String compound,
                                 final Double[] bandGap,
                                 final String color,
                                 final String compoundOp,
                                 final String bandGapOp,
                                 final String colorOp) {

        FormulaQuery formulaQuery = getFormulaQuery(compound, bandGap, color, compoundOp, bandGapOp, colorOp);
        return materialMapper.search(formulaQuery);
    }

    FormulaQuery getFormulaQuery(String compound, Double[] bandGap, String color, String compoundOp, String bandGapOp, String colorOp) {
        Double bandGapValue1 = null;
        Double bandGapValue2 = null;
        colorOp = (colorOp == null) ? Criterion.Like.toString() : colorOp;
        compoundOp = (compoundOp == null) ? Criterion.Like.toString() : compoundOp;

        if (bandGap != null && bandGap[0] != null && bandGap[1] != null) {
            bandGapValue1 = bandGap[0];
            bandGapValue2 = bandGap[1];
            bandGapOp = null;
        } else if (bandGap != null && bandGap[0] != null) {
            bandGapValue1 = bandGap[0];
        } else if (bandGap != null && bandGap[1] != null) {
            bandGapValue2 = bandGap[1];
        }

        if ((colorOp).equals(Criterion.Like.toString())) {
            color = template(color);
        }
        if ((compoundOp).equals(Criterion.Like.toString())) {
            compound = template(compound);
        }

        return FormulaQuery.builder()
                .bandGapValue1(bandGapValue1)
                .bandGapValue2(bandGapValue2)
                .color(color)
                .compound(compound)
                .compoundOp(LOOK_UP_BY_API_VERB.get(compoundOp))
                .colorOp(LOOK_UP_BY_API_VERB.get(colorOp))
                .bandGapOp(LOOK_UP_BY_API_VERB.get(bandGapOp))
                .build();
    }

    String template(String value) {
        if (value != null)
            return MessageFormat.format("%{0}%", value);
        return null;
    }


}
