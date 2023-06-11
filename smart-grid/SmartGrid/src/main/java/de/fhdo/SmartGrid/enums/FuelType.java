package de.fhdo.SmartGrid.enums;

public enum FuelType {
    OIL {
        @Override
        public double getEfficiency() {
            return 0.40; // 40%
        }
    },
    COAL {
        @Override
        public double getEfficiency() {
            return 0.36; // 36%
        }
    },
    GAS {
        @Override
        public double getEfficiency() {
            return 0.50; // 50%
        }
    };

    // Jeder Kraftstofftyp muss diese Methode implementieren
    public abstract double getEfficiency();
}
