package de.telekom.sea2.lookup;

public enum Salutation {
        MR,
        MRS,
        OTHER;

        public static Salutation fromString(String string) throws IllegalAccessException {
            switch (string) {
                case "mr":
                case "MR":
                case "Mr":
                case "mister":
                case "Mister":
                    return MR;
                case "MRS":
                case "mrs":
                case "Mrs":
                case "Misses":
                case "misses":
                    return MRS;
                case "OTHER":
                case "other":
                case "Other":
                case "diverse":
                case "DIVERSE":
                case "Diverse":
                    return OTHER;
                default:
                    throw new IllegalAccessException("Unexpected value: " + string);

            }
        }

        @Override
        public String toString() {
            switch (this) {
                case MRS:
                    return "Woman";
                case MR:
                    return "Men";
                case OTHER:
                    return "Other";
                default:
                    return "Other";
            }
        }

        private Salutation(){

        }
    }

