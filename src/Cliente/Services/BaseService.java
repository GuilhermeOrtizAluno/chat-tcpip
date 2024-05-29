package Cliente.Services;

import com.google.gson.Gson;

import java.util.Scanner;

public class BaseService {
    protected static final Gson _gson = new Gson();
    protected static final Scanner _scanner = new Scanner(System.in);
}
