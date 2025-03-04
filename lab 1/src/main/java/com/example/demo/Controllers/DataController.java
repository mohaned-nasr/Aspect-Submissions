package com.example.demo.Controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {

    // GET method: Retrieve data
    @GetMapping("/{id}")
    public ResponseEntity<String> getData(@PathVariable("id") int id) {
        // Logic: Simply returns the data by id
        String data = "Data for ID: " + id;
        return ResponseEntity.ok(data);
    }

    // POST method: Create new data
    @PostMapping
    public ResponseEntity<String> createData(@RequestBody Map<String, Object> newData) {
        // Logic: Process the incoming JSON data
        String data = "Created data: " + newData.toString();
        return ResponseEntity.ok(data);
    }



    // PUT method: Update existing data
    @PutMapping("/{id}")
    public ResponseEntity<String> updateData(@PathVariable("id") int id, @RequestBody String updatedData) {
        // Logic: Mock updating the data for the given id
        String data = "Updated data for ID: " + id + " to: " + updatedData;
        return ResponseEntity.ok(data);
    }

    // DELETE method: Delete data by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable("id") int id) {
        // Logic: Mock deletion of data by id
        String data = "Deleted data for ID: " + id;
        return ResponseEntity.ok(data);
    }
}
