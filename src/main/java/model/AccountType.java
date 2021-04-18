package model;

import javax.persistence.*;

@Entity
@Table(name = "account_types")
public class AccountType {

    @Id
    @SequenceGenerator(name = "accountType_seq", sequenceName = "accountType_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountType_seq")
    @Column(name = "type_id")
    private long TypeId;

    @Column(name = "type_name")
    private String TypeName;

    @Column(name = "max_credit")
    private long MaxCredit;

    @Column(name = "profitability")
    private long Profitability;

    @Column(name = "additional_info")
    private String AdditionalInfo;

    public AccountType() {

    }

    public AccountType(String TypeName, long MaxCredit, long Profitability, String AdditionalInfo) {
        this.TypeName = TypeName;
        this.MaxCredit = MaxCredit;
        this.Profitability = Profitability;
        this.AdditionalInfo = AdditionalInfo;
    }

    public long getTypeId() {
        return TypeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public long getMaxCredit() {
        return MaxCredit;
    }

    public long getProfitability() {
        return Profitability;
    }

    public String getAdditionalInfo() {
        return AdditionalInfo;
    }

    public void setTypeId(long typeId) {
        TypeId = typeId;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public void setMaxCredit(long maxCredit) {
        MaxCredit = maxCredit;
    }

    public void setProfitability(long profitability) {
        Profitability = profitability;
    }

    public void setAdditionalInfo(String additionalInfo) {
        AdditionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "AccountType{" + "TypeId=" + TypeId + ", TypeName=" + TypeName + "}";
    }
}
