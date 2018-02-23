package pro.anuj.citrine.persistence;

import lombok.Getter;

@Getter
public class FormulaQuery {
    private final String compound;
    private final String color;
    private final Double bandGapValue1;
    private final Double bandGapValue2;
    private final String compoundOp;
    private final String colorOp;
    private final String bandGapOp;

    FormulaQuery(final String compound,
                 final String color,
                 final Double bandGapValue1,
                 final Double bandGapValue2,
                 final String compoundOp,
                 final String colorOp,
                 final String bandGapOp) {
        this.compound = compound;
        this.color = color;
        this.bandGapValue1 = bandGapValue1;
        this.bandGapValue2 = bandGapValue2;
        this.compoundOp = compoundOp;
        this.colorOp = colorOp;
        this.bandGapOp = bandGapOp;
    }

    public static FormulaQueryBuilder builder() {
        return new FormulaQueryBuilder();
    }

    public static class FormulaQueryBuilder {
        private String compound;
        private String color;
        private Double bandGapValue1;
        private Double bandGapValue2;
        private String compoundOp;
        private String colorOp;
        private String bandGapOp;

        public FormulaQueryBuilder compound(String compound) {
            this.compound = (compound != null) ? compound.toLowerCase() : null;
            return this;
        }

        public FormulaQueryBuilder compoundOp(Criterion compoundOp) {
            if (compoundOp != null)
                this.compoundOp = compoundOp.toString();
            return this;
        }

        public FormulaQueryBuilder colorOp(Criterion colorOp) {
            if (colorOp != null)
                this.colorOp = colorOp.toString();
            return this;
        }

        public FormulaQueryBuilder bandGapOp(Criterion bandGapOp) {
            if (bandGapOp != null)
                this.bandGapOp = bandGapOp.toString();
            return this;
        }

        public FormulaQueryBuilder color(String color) {
            this.color = (color != null) ? color.toLowerCase() : null;
            return this;
        }

        public FormulaQueryBuilder bandGapValue1(Double bandGapValue1) {
            this.bandGapValue1 = bandGapValue1;
            return this;
        }

        public FormulaQueryBuilder bandGapValue2(Double bandGapValue2) {
            this.bandGapValue2 = bandGapValue2;
            return this;
        }


        public FormulaQuery build() {
            return new FormulaQuery(compound, color, bandGapValue1, bandGapValue2, compoundOp, colorOp, bandGapOp);
        }

        public String toString() {
            return "FormulaQuery.FormulaQueryBuilder(compound=" + this.compound + ", color=" + this.color + ", bandGapValue1=" + this.bandGapValue1 + ", bandGapValue2=" + this.bandGapValue2 + ")";
        }

    }
}
