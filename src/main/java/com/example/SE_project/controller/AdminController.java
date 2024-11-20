package com.example.SE_project.controller;

import com.example.SE_project.dto.adminDTO;
import com.example.SE_project.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.format.Printer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService ;

    @GetMapping("/{id}")
    public ResponseEntity<adminDTO> find_name_email_admin(@PathVariable String id){
        return new ResponseEntity<>(adminService.find_admin_information(id), HttpStatus.OK);
    }

    @GetMapping("/getAllPrinter")
    public ResponseEntity<?> getALLprinter(){
        return new ResponseEntity<>(adminService.get_all_printer() , HttpStatus.OK);
    }

    @GetMapping("/getAllPrintRequest")
    public ResponseEntity<?> getAllPrintRequest() {
        return new ResponseEntity<>(adminService.get_all_print_request() , HttpStatus.OK);
    }

    @GetMapping("/getAllRequestOnPrinter/{printer_id}")
    public ResponseEntity<?> getAllPrintRequestOnPrinter(@PathVariable String printer_id){
        return new ResponseEntity<>(adminService.get_all_print_request_by_printer_id(printer_id), HttpStatus.OK);
    }

    @GetMapping("/getAllRequestOnStudent/{std_id}")
    public ResponseEntity<?> getAllPrintRequestOnStudent(@PathVariable String std_id){
        return new ResponseEntity<>(adminService.get_all_print_request_by_student_id(std_id), HttpStatus.OK);
    }

    @PostMapping("/insertNewPrinter")
    public ResponseEntity<?> insertNewPrinter(
            @RequestParam String building,
            @RequestParam String model,
            @RequestParam String importDateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = sdf.parse(importDateString);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return new ResponseEntity<>(adminService.insertNewPrinter(building,model,sqlDate),HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<?>newsemester(@RequestParam String semester
    ,@RequestParam Integer numOfPaperDefault)
    {
        return new ResponseEntity<>(adminService.reset(semester,numOfPaperDefault),
                HttpStatus.OK);

    }

    @PutMapping("/setPrinterState")
    public ResponseEntity<?> setPrinterState (
            @RequestParam String printer_id
    ){
        return new ResponseEntity<>(adminService.updateStatePrinter(printer_id),HttpStatus.OK);
    }
    @PutMapping("/acceptPrintRequest")
    public ResponseEntity<?> acceptPrintRequest (
            @RequestParam String printer_id, @RequestParam String file_id
    ){
        return new ResponseEntity<>(adminService.acceptPrint(printer_id,file_id),HttpStatus.OK);
    }

    // status 0 dang cho  , status 1 la tu choi , status 2 la chap nhan
    @PutMapping("/refusePrintRequest")
    public ResponseEntity<?> refusePrintRequest(
            @RequestParam String printer_id ,
            @RequestParam String file_id
    )
    {
        return new ResponseEntity<>(adminService.refusePrint(printer_id,file_id, 1),HttpStatus.OK);
    }





}
