package com.kavito.binary;

public enum NumEnum {

    ONE("one","一",1),
    TWO("two","二",2),
    THREE("three","三",3),
    FOUR("four","四",4),
    ;



    ;
    private String code;
    private String name;
    private int index;


    NumEnum(String code, String name, int index) {
        this.code = code;
       this.name = name;
        this.index = index;
    }


    /**
     * 根据index获取枚举
     * @param index
     * @return
     */
    public static NumEnum getEnumByIndex(int index){
        for (NumEnum numEnum:NumEnum.values()) {
            if(numEnum.getIndex()==index){
                return numEnum;
            }
        }
        return null;
    }

    /**
     * 根据code获取枚举
     * @param code
     * @return
     */
    public static NumEnum getEnumBycode(String code){
        for (NumEnum numEnum:NumEnum.values()) {
            if(numEnum.getCode()==code){
                return numEnum;
            }
        }
        return null;
    }


    /**
     * 获取最大偏移量
     * @return
     */
    public static int maxNum(){
        int maxNum = 0;
        for(NumEnum numEnum:NumEnum.values()){
            if(maxNum < numEnum.getIndex()){
                maxNum = numEnum.getIndex();
            }
        }
        return maxNum;
    }



    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
