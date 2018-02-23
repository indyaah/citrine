package pro.anuj.citrine.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pro.anuj.citrine.domain.Material;
import pro.anuj.citrine.persistence.Criterion;
import pro.anuj.citrine.persistence.FormulaQuery;
import pro.anuj.citrine.persistence.MaterialMapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class MaterialServiceTest {

    private final MaterialMapper materialMapperMock = mock(MaterialMapper.class);
    private final Material materialMock = mock(Material.class);
    private MaterialService materialService = new MaterialService(materialMapperMock);

    @Test
    public void testInsert() {
        when(materialMapperMock.save(materialMock)).thenReturn(1);

        int save = materialService.save(materialMock);

        assertEquals(save, 1);

    }

    @Test
    @Parameters({
            "1.0, 2.0",
            "0.0, 10.0",
            "null , 10.0",
            "1.0 , null",
            "null , null"
    })
    public void testBandGapsMain(String bandgap1, String bandgap2) {
        Double bandgap1_ = (bandgap1.equals("null")) ? null : Double.valueOf(bandgap1);
        Double bandgap2_ = (bandgap2.equals("null")) ? null : Double.valueOf(bandgap2);
        testBandGaps("", new Double[]{bandgap1_, bandgap2_}, "", Criterion.Like.toString(), Criterion.Equals.toString(), Criterion.Like.toString());
    }

    private void testBandGaps(String compound, Double[] doubles, String color, String compoundOp, String bandgapOp, String colorOp) {
        FormulaQuery formulaQuery = materialService.getFormulaQuery(compound, doubles, color, compoundOp, bandgapOp, colorOp);
        assertEquals(formulaQuery.getBandGapValue1(), doubles[0]);
        assertEquals(formulaQuery.getBandGapValue2(), doubles[1]);
        if (doubles[0] != null && doubles[1] != null)
            assertNull(formulaQuery.getBandGapOp());
        else
            assertNotNull(formulaQuery.getBandGapOp());
    }


    @Test
    @Parameters({
            "a, like",
            "a, eq",
            "null, like",
            "null, eq"
    })
    public void testCompoundMain(String compound, String compoundOP) {
        compound = (compound.equals("null")) ? null : compound;
        compoundOP = (compoundOP.equals("null")) ? null : compoundOP;
        testCompound(compound, new Double[]{1.0, 1.2}, "", compoundOP, Criterion.Equals.toString(), Criterion.Like.toString());
    }

    private void testCompound(String compound, Double[] doubles, String color, String compoundOp, String bandgapOp, String colorOp) {
        FormulaQuery formulaQuery = materialService.getFormulaQuery(compound, doubles, color, compoundOp, bandgapOp, colorOp);
        if (Criterion.Like.toString().equals(compoundOp))
            compound = materialService.template(compound);
        assertEquals(formulaQuery.getCompound(), compound);
        assertEquals(formulaQuery.getCompoundOp(), compoundOp);
    }


}