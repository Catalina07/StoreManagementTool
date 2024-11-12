package storeManagementTool.Dtos;

import lombok.Data;

@Data
public class UserEntityToDTO {

    private long id;

    private String name;

    private String email;

    private String phoneNumber;
}
