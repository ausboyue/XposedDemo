package cn.icheny.xposed;

import android.app.Activity;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findField;

public class HookLogin implements IXposedHookLoadPackage {
    private static final String TAG = "HookLogin";

    //    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
//
//        if (lpparam == null) {
//            return;
//        }
//
//        Log.e(TAG, "Load app packageName:" + lpparam.packageName);
//
//        /**
//         * 过滤非目标应用
//         */
//        if (!"cn.icheny.logindemo".equals(lpparam.packageName)) {
//            return;
//        }
//
//        //固定格式
//        XposedHelpers.findAndHookMethod(
//                "cn.icheny.logindemo.LoginActivity", // 需要hook的包名+类名
//                lpparam.classLoader,                            // 类加载器，固定这么写就行了
//                "checkLogin",                     // 需要hook的方法名，checkLogin(username,password)
//                String.class,                                   // 第一个参数，用户名
//                String.class,                                   // 第二个参数，密码
//                // Hook回调
//                new XC_MethodHook() {
//                    @Override
//                    /**
//                     * checkLogin被hook前执行下面的方法
//                     */
//                    protected void beforeHookedMethod(MethodHookParam param) {
//                        Log.e(TAG, "劫持开始了↓↓↓↓↓↓");
//                    }
//
//                    /**
//                     * checkLogin被hook后执行下面的方法
//                     */
//                    protected void afterHookedMethod(MethodHookParam param) {
//
//                        // hook 用户名和密码
//                        String username = (String) param.args[0];
//                        String password = (String) param.args[1];
//                        Log.e(TAG, "用户名:" + username + "     密码:" + password);
//
//                        // 被hook后返回自己指定的值（true，表示方法checkLogin调用返回值为true）
//                        param.setResult(true);
//
//                        Log.e(TAG, "劫持结束了↑↑↑↑↑↑");
//                    }
//                }
//        );
//    }
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {

        if (lpparam == null) {
            return;
        }

        Log.e(TAG, "Load app packageName:" + lpparam.packageName);

        /**
         * 过滤非目标应用
         */
        if (!"com.ruanmei.ithome".equals(lpparam.packageName)) {
            return;
        }

        //固定格式
        XposedHelpers.findAndHookMethod(
                "com.ruanmei.ithome.ui.UserCenterActivity", // 需要hook的包名+类名
                lpparam.classLoader,                            // 类加载器，固定这么写就行了
                "login",                     // 需要hook的方法名，login()
                // Hook回调
                new XC_MethodHook() {
                    @Override
                    /**
                     * 指定的方法被hook前执行下面的方法
                     */
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.e(TAG, "劫持开始了↓↓↓↓↓↓");
                    }

                    /**
                     * 指定的方法被hook后执行下面的方法
                     */
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        Class o = param.thisObject.getClass();
                        Field.setAccessible(o.getDeclaredFields(), true);
                        Field usernameField = findField(o, "et_user_username");
                        Field passwordField = findField(o, "et_user_password");
                        EditText et_user_username = (EditText) usernameField.get(param.thisObject);
                        EditText et_user_password = (EditText) passwordField.get(param.thisObject);
                        String username = et_user_username.getText().toString();
                        String password = et_user_password.getText().toString();
                        Log.e(TAG, "用户名: " + username + "  , 密码 : " + password);

                        Log.e(TAG, "劫持结束了↑↑↑↑↑↑");
                    }
                }
        );
    }
}
