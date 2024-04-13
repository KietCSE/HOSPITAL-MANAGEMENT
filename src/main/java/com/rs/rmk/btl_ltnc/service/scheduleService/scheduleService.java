package com.rs.rmk.btl_ltnc.service.scheduleService;

import com.rs.rmk.btl_ltnc.model.doctorInfo.doctorInfoModel;
import com.rs.rmk.btl_ltnc.model.task.taskModel;
import com.rs.rmk.btl_ltnc.repository.doctorInfoRepository.doctorInfoRepository;
import com.rs.rmk.btl_ltnc.repository.scheduleRepository.scheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class scheduleService  {
    @Autowired
    private scheduleRepository scheduleRepository;
    @Autowired
    private doctorInfoRepository doctorInfoRepository;

    private static String getDateOfWeek(String Day) throws ParseException {
        Date day = new SimpleDateFormat("dd/MM/yyyy").parse(Day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String date = dayOfWeek == 1 ? "CN" : "Thu" + Integer.toString(dayOfWeek);
        return date;
    }

    private static int toIndex(String time) {
        int index = time.indexOf(':');
        int hours = Integer.parseInt(time.substring(0, index));
        int minutes = Integer.parseInt(time.substring(index + 1));
        if (minutes <= 30) {
            return 2 * hours;
        }
        else {
            return 2 * hours + 1;
        }
    }

    public List<List<taskModel>> getSchedule(String doctorID) throws ExecutionException, InterruptedException {
        return scheduleRepository.getSchedule(doctorID);
    }
//
//    public List<String> dayAndTime(String day, String from, String to, String departmentName) throws ExecutionException, InterruptedException, ParseException {
//        List<String> listIDs = doctorInfoRepository.getListDoctorID(departmentName);
//        String date = getDateOfWeek(day);
//        int[] arr = new int[48];
//        for (String ID : listIDs) {
//            List<taskModel> tasks = scheduleRepository.getTaskListAtDay(ID, day, date);
//            for (taskModel task : tasks) {
//                int left = toIndex(task.getFrom());
//                int right = toIndex(task.getTo());
//                for (int i = left; i <= right; i++) {
//
//                }
//            }
//        }
//    }

    public taskModel addTask(String doctorID, taskModel task) throws ExecutionException, InterruptedException, ParseException {
        String date = getDateOfWeek(task.getDay());
        //Set id for task
        LocalTime now = LocalTime.now();
        task.setId(now.toString());
        return scheduleRepository.addTask(doctorID, task, date);
    }

    public boolean deleteTask(String doctorID, taskModel task) throws ExecutionException, InterruptedException, ParseException {
        String date = getDateOfWeek(task.getDay());
        return scheduleRepository.deleteTask(doctorID, task, date);
    }
}