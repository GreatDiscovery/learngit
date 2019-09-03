package com.example.main.common;

/**
 * @author gavin
 * @date 2019/4/1 18:50
 */
public enum SEX {
    MALE("男", 1),
    FEMALE("女", 0);

    SEX(String sexName, Integer sexType) {
        this.sexName = sexName;
        this.sexType = sexType;
    }

    private String sexName;
    private Integer sexType;

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public Integer getSexType() {
        return sexType;
    }

    public void setSexType(Integer sexType) {
        this.sexType = sexType;
    }

    @Override
    public String toString() {
        return "SEX{" +
                "sexName='" + sexName + '\'' +
                ", sexType=" + sexType +
                '}';
    }
}
