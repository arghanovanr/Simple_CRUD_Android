package shadowspy.developement.crudfirebase;

public class ModelVehicle {
    private String username,vehicleType,vehicleBrand,key;

    private ModelVehicle(){

    }

    public ModelVehicle(String name, String vehicleType, String vehicleBrand) {
        this.username = name;
        this.vehicleType = vehicleType;
        this.vehicleBrand = vehicleBrand;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
