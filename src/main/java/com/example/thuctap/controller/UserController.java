package com.example.thuctap.controller;


import com.example.thuctap.dto.PageDTO;
import com.example.thuctap.dto.ResponseDTO;
import com.example.thuctap.dto.SearchDTO;
import com.example.thuctap.dto.UserDTO;
import com.example.thuctap.entity.User;
import com.example.thuctap.repository.UserRepository;
import com.example.thuctap.service.UserService;
import com.example.thuctap.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping("/search")
    public ResponseDTO<PageDTO<UserDTO>> searchUserByName(@RequestBody SearchDTO searchDTO){
        PageDTO<UserDTO> pageDTO = userService.searchByNameOrUserName(searchDTO);
        return ResponseDTO.<PageDTO<UserDTO>>builder()
                .status(200)
                .msg("OK")
                .data(pageDTO)
                .build();
    }

    @PostMapping("/")
    public ResponseDTO<Void> newUser(@RequestBody @Valid UserDTO userDTO){
        userService.create(userDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("Create successfully!!")
                .build();
    }

    @PutMapping("/")
    public ResponseDTO<Void> updateUser(@RequestBody @Valid UserDTO userDTO){
        userService.update(userDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("OK")
                .build();
    }

    @GetMapping("/")
    public ResponseDTO<UserDTO> getById(@RequestParam("id") int id){
        return ResponseDTO.<UserDTO>builder()
                .status(200)
                .msg("OK")
                .data(userService.getById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") int id){
        userService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("OK")
                .build();
    }
    @PostMapping("/list")
    public ResponseDTO<List<UserDTO>> list() {
        List<UserDTO> usersDTO = userService.getAll();
        return ResponseDTO.<List<UserDTO>>builder()
                .status(200)
                .msg("OK")
                .data(usersDTO).build();
    }
    @PostMapping("/pagination/{offset}/{pageSize}/{field}")
    public ResponseDTO<PageDTO<UserDTO>> getUserWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        PageDTO<UserDTO> usersWithPaginationAndSort = userServiceImpl.getUserWithPaginationAndSort(offset, pageSize,field);
        return ResponseDTO.<PageDTO<UserDTO>>builder()
                .status(200)
                .msg("OK")
                .data(usersWithPaginationAndSort).build();
    }
    @PostMapping("/pagination/{offset}/{pageSize}")
    public ResponseDTO<PageDTO<UserDTO>> getUserWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        PageDTO<UserDTO> usersWithPagination = userServiceImpl.getUserWithPagination(offset, pageSize);
        return ResponseDTO.<PageDTO<UserDTO>>builder()
                .status(200)
                .msg("OK")
                .data(usersWithPagination).build();
    }

//    @GetMapping("/csvexport")
//    public void exportCSV(HttpServletResponse response) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
//        //set file name;
//        String fileName = "User-data.csv";
//        response.setContentType("text/csv");
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + fileName + "\"");
//        //create a csv writer
//        StatefulBeanToCsv<UserDTO> writer = new StatefulBeanToCsvBuilder<UserDTO>(response.getWriter())
//                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false)
//                .build();
//        //write all employees data to csv file
//        writer.write(userService.getAll());
//    }
@GetMapping("/export-to-excel")
public void exportToExcel(HttpServletResponse response) throws IOException {
    response.setContentType("application/octet-stream");
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=Customers_Information.xlsx";
    response.setHeader(headerKey, headerValue);
    userServiceImpl.exportCustomerToExcel(response);

}



}
