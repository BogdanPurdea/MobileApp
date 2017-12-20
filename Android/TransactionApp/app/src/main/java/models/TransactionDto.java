package models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bogdan on 10/29/2017.
 */
public class TransactionDto implements Parcelable{
    public Integer id;
    public String category;
    public String type;
    public Integer value;

    public TransactionDto(String category, String type, Integer value){
        this.category = category;
        this.type = type;
        this.value = value;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return "Transaction "+getId()+"\nCategory: "+getCategory()+"\nType: "+getType()+"\nValue: "+getValue();
    }
    public String getIdString()
    {
        return ""+getId();
    }
    public String getCategoryString()
    {
        return ""+getCategory();
    }
    public String getTypeString()
    {
        return ""+getType();
    }
    public String getValueString()
    {
        return ""+getValue();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(category);
        out.writeString(type);
        out.writeInt(value);
    }

    public static final Parcelable.Creator<TransactionDto> CREATOR = new Parcelable.Creator<TransactionDto>() {
        public TransactionDto createFromParcel(Parcel in) {
            return new TransactionDto(in);
        }

        public TransactionDto[] newArray(int size) {
            return new TransactionDto[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private TransactionDto(Parcel in) {
        id = in.readInt();
        category = in.readString();
        type = in.readString();
        value = in.readInt();
    }
}
