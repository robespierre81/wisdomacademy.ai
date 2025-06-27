package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.CourseBookingsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseBookingsViewRepository extends JpaRepository<CourseBookingsView, Long> {

    // ✅ Find courses booked by user
    List<CourseBookingsView> findByBookedBy(String walletAddress);

    // ✅ Find courses created by user
    List<CourseBookingsView> findByCourseCreator(String walletAddress);

    // ✅ Find completed courses
    List<CourseBookingsView> findByBookedByAndBookingStatus(String walletAddress, String bookingStatus);

    // ✅ Find new courses (not older than 2 weeks) that the user hasn't booked
    @Query("SELECT c FROM CourseBookingsView c WHERE c.courseCreatedAt >= :twoWeeksAgo AND (c.bookedBy IS NULL OR c.bookedBy <> :walletAddress)")
    List<CourseBookingsView> findNewCourses(@Param("walletAddress") String walletAddress, @Param("twoWeeksAgo") LocalDateTime twoWeeksAgo);
}
