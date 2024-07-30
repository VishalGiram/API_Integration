package POJOClasses;

public class AddBatch {
	private String batchHoldingDetailsId;
    private String holdingId;
    private String receivingAccountNumber;
    private String proposedQuantity;

    // Getters and Setters
    public String getBatchHoldingDetailsId() {
        return batchHoldingDetailsId;
    }

    public void setBatchHoldingDetailsId(String batchHoldingDetailsId) {
        this.batchHoldingDetailsId = batchHoldingDetailsId;
    }

    public String getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(String holdingId) {
        this.holdingId = holdingId;
    }

    public String getReceivingAccountNumber() {
        return receivingAccountNumber;
    }

    public void setReceivingAccountNumber(String receivingAccountNumber) {
        this.receivingAccountNumber = receivingAccountNumber;
    }

    public String getProposedQuantity() {
        return proposedQuantity;
    }

    public void setProposedQuantity(String proposedQuantity) {
        this.proposedQuantity = proposedQuantity;
    }
}
