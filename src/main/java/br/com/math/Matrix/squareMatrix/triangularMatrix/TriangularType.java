package br.com.math.matrix.squareMatrix.triangularMatrix;

public enum TriangularType {
    DIAGONAL {
        @Override
        public boolean validatePosition(int i, int j) {
            return i == j;
        }

    },
    LOWER {
        @Override
        public boolean validatePosition(int i, int j) {
            return i >= j;
        }
    },
    UPPER {
        @Override
        public boolean validatePosition(int i, int j) {
            return i <= j;
        }
    };

    public abstract boolean validatePosition(int i, int j);
}
