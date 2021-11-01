package br.com.itau.selective.process.commons.constant;

public class ApplicationConstant {

    public interface Password{
        String BASIC_PATH = "/password";

        interface V1{
            String PASSWORD_VERSION = BASIC_PATH + "/v1";
            String PASSWORD_VALIDATE = PASSWORD_VERSION + "/validate";
        }
    }
}
