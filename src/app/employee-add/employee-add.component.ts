import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-add',
  templateUrl: './employee-add.component.html',
  styleUrls: ['./employee-add.component.css']
})
export class EmployeeAddComponent implements OnInit {

  constructor(private employeeService:EmployeeService,private router:Router) { }
  employee:Employee=new Employee();
  ngOnInit(): void {
  }
 onSubmit(){
   this.addEmployeeList();
   this.gotoHome();
 }
 addEmployeeList(){
   this.employeeService.addEmployee(this.employee).subscribe(data=>{
     console.log(data);
    
   });
 }
 gotoHome(){
   this.router.navigate(['/employees']);
 }
}
