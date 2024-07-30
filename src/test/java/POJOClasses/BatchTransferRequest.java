package POJOClasses;

import java.util.List;

public class BatchTransferRequest {
	private String batchName;
    private String comment;
    private List<String> selectedHoldingIds;
    private String accountNumber;
    private String transferTypeId;

    // Getters and Setters
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getSelectedHoldingIds() {
        return selectedHoldingIds;
    }

    public void setSelectedHoldingIds(List<String> selectedHoldingIds2) {
        this.selectedHoldingIds = selectedHoldingIds2;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(String transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

}
