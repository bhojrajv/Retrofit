package Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.retrofit.Model.User;
import com.example.retrofit.Response.UserResponse;

public class SharedPrefmanager {
    private final static String Pref_name="User_pref";
   public static SharedPrefmanager mInstance;
    public Context context;

    public SharedPrefmanager(Context context)
    {
        this.context=context;
    }
    public static synchronized SharedPrefmanager getmInstance(Context context){
        if(mInstance==null)
        {
            mInstance=new SharedPrefmanager(context);
        }
        return mInstance;
    }

 public void saveuser(User user){

     SharedPreferences sharedPreferences=context.getSharedPreferences(Pref_name,Context.MODE_PRIVATE);
     SharedPreferences.Editor editor=sharedPreferences.edit();
     editor.putInt("id",user.getId() );
     editor.putString("email",user.getEmail());
     editor.putString("name",user.getName());
     editor.putString("school",user.getSchool());
     editor.apply();
 }
 public boolean isLogin(){
     SharedPreferences sharedPreferences=context.getSharedPreferences(Pref_name,Context.MODE_PRIVATE);
     return sharedPreferences.getInt("id",-1)!=-1;
 }
 public User getuser()
 {
     SharedPreferences sharedPreferences=context.getSharedPreferences(Pref_name,Context.MODE_PRIVATE);

     User user=new User(
        sharedPreferences.getInt("id",-1),
             sharedPreferences.getString("email",null),
             sharedPreferences.getString("name",null),
             sharedPreferences.getString("school",null)
     );

     return user;

 }
public void clear(){
    SharedPreferences sharedPreferences=context.getSharedPreferences(Pref_name,Context.MODE_PRIVATE);
      SharedPreferences.Editor editor=sharedPreferences.edit();
      editor.clear();
      editor.apply();
}
}
