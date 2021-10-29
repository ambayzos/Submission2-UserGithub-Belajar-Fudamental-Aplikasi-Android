
package com.example.latihan.submissiondicodingusergithub.entity;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated("org.parceler.ParcelAnnotationProcessor")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class UserEntity$$Parcelable
    implements Parcelable, ParcelWrapper<com.example.latihan.submissiondicodingusergithub.entity.UserEntity>
{

    private com.example.latihan.submissiondicodingusergithub.entity.UserEntity userEntity$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<UserEntity$$Parcelable>CREATOR = new Creator<UserEntity$$Parcelable>() {


        @Override
        public UserEntity$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new UserEntity$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public UserEntity$$Parcelable[] newArray(int size) {
            return new UserEntity$$Parcelable[size] ;
        }

    }
    ;

    public UserEntity$$Parcelable(com.example.latihan.submissiondicodingusergithub.entity.UserEntity userEntity$$2) {
        userEntity$$0 = userEntity$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(userEntity$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.example.latihan.submissiondicodingusergithub.entity.UserEntity userEntity$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(userEntity$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(userEntity$$1));
            parcel$$1 .writeString(userEntity$$1 .avatarUrl);
            parcel$$1 .writeInt(userEntity$$1 .id);
            parcel$$1 .writeString(userEntity$$1 .login);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.example.latihan.submissiondicodingusergithub.entity.UserEntity getParcel() {
        return userEntity$$0;
    }

    public static com.example.latihan.submissiondicodingusergithub.entity.UserEntity read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.example.latihan.submissiondicodingusergithub.entity.UserEntity userEntity$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            userEntity$$4 = new com.example.latihan.submissiondicodingusergithub.entity.UserEntity();
            identityMap$$1 .put(reservation$$0, userEntity$$4);
            userEntity$$4 .avatarUrl = parcel$$3 .readString();
            userEntity$$4 .id = parcel$$3 .readInt();
            userEntity$$4 .login = parcel$$3 .readString();
            com.example.latihan.submissiondicodingusergithub.entity.UserEntity userEntity$$3 = userEntity$$4;
            identityMap$$1 .put(identity$$1, userEntity$$3);
            return userEntity$$3;
        }
    }

}
