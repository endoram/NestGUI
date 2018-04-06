public class NestInformation {

    public String code;
    public String name;
    public double ambient;
    public double target;
    public TempSystem tempSystem;
    public String tempSystemStr;
    
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getAmbient() {
        return ambient;
    }

    public double getTarget() {
        return target;
    }

    public TempSystem getTempSystem() {
        return tempSystem;
    }

    public String getTempSystemStr() {
        return tempSystemStr;
    }

    public NestInformation(String code, String name, double ambient, double target, TempSystem tempSystem, String tempSystemStr) {
        this.code = code;
        this.name = name;
        this.ambient = ambient;
        this.target = target;
        this.tempSystem = tempSystem;
        this.tempSystemStr = tempSystemStr;
    }

}