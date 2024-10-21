package com.porto.todo.task;

import lombok.Getter;

@Getter
public enum Priority {
        LOW(0, "LOW"), MEDIUM(1, "MEDIUM"), HIGH(2, "HIGH");

        private Integer cod;
        private String description;


        Priority(Integer cod, String description) {
            this.cod = cod;
            this.description = description;
        }

    public void setCod(Integer cod) {
            this.cod = cod;
        }

    public void setDescription(String description) {
            this.description = description;
        }

        public static Priority toEnum(Integer cod){
            if(cod == null) {
                return null;
            }
            for (Priority x : Priority.values()){
                if (cod.equals(x.getCod())) {
                    return x;
                }
            }

            throw new IllegalArgumentException("Invalid Priority!");
        }

}
