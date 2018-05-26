package com.company.reflection;

import java.util.Objects;

public class TestReflection {

    private String field1;
    private String field2;

    public TestReflection() {
        field1 = "field1";
        field2 = "field2";
    }

    public TestReflection(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Getter
    public String getField1() {
        return field1;
    }

    @Getter
    public String getField2() {
        return field2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestReflection)) return false;
        TestReflection that = (TestReflection) o;
        return Objects.equals(getField1(), that.getField1()) &&
                Objects.equals(getField2(), that.getField2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getField1(), getField2());
    }

    @Override
    public String toString() {
        return "TestReflection{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                '}';
    }
}
