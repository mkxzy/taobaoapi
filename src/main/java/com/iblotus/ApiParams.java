package com.iblotus;

/**
 * Api参数工厂方法
 */
public final class ApiParams {

    public static ApiParam stringParam(final String s){

        return new ApiParam() {

            public String format() {
                return s;
            }
        };
    }

    public static ApiParam arrayParam(final String... array){

//        return () -> String.join(",", array);
        return new ApiParam() {
            public String format() {
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< array.length;i++){
                    if(i > 0)
                        sb.append(",");
                    sb.append(array[i]);
                }
                return sb.toString();
            }
        };
    }

    public static ApiParam integerParam(final int p){

//        return () -> String.valueOf(p);
        return new ApiParam() {
            public String format() {
                return String.valueOf(p);
            }
        };
    }
}