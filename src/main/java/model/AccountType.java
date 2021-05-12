package model;

import javax.persistence.*;

@Entity
@Table(name = "account_types")
public class AccountType {

    @Id
    @SequenceGenerator(name = "accountType_seq", sequenceName = "accountType_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountType_seq")
    @Column(name = "type_id")
    private long typeId;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "max_credit")
    private long maxCredit;

    @Column(name = "profitability")
    private long profitability;

    @Column(name = "additional_info")
    private String additionalInfo;

    public AccountType() {

    }

    public AccountType(String TypeName, long MaxCredit, long Profitability, String AdditionalInfo) {
        this.typeName = TypeName;
        this.maxCredit = MaxCredit;
        this.profitability = Profitability;
        this.additionalInfo = AdditionalInfo;
    }

    public long getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public long getMaxCredit() {
        return maxCredit;
    }

    public long getProfitability() {
        return profitability;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setMaxCredit(long maxCredit) {
        this.maxCredit = maxCredit;
    }

    public void setProfitability(long profitability) {
        this.profitability = profitability;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "AccountType{" + "TypeId=" + typeId + ", TypeName=" + typeName + "}";
    }
}
