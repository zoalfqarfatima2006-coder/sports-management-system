class Request {
    String userId;
    String equipmentName;
    String status;

    Request(String userId, String equipmentName) {
        this.userId = userId;
        this.equipmentName = equipmentName;
        this.status = "Pending";
    }
}