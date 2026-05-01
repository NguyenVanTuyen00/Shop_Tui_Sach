package utils;

public class SessionManager {
    private static String maNV;
    private static String vaiTro;

    public static void setLoginSession(String user, String role) {
        maNV = user;
        vaiTro = role;
    }

    public static String getMaNV() { return maNV; }
    public static String getVaiTro() { return vaiTro; }
    
    public static void clear() { maNV = null; vaiTro = null; }
}