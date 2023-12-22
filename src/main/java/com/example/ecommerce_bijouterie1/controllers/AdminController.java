package com.example.ecommerce_bijouterie1.controllers;


import com.example.ecommerce_bijouterie1.constants.Pages;
import com.example.ecommerce_bijouterie1.constants.PathConstants;
import com.example.ecommerce_bijouterie1.dto.request.SearchRequest;
import com.example.ecommerce_bijouterie1.services.AdminService;
import com.example.ecommerce_bijouterie1.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(PathConstants.ADMIN)
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@EnableAutoConfiguration
@Configurable
@Configuration
@Component
public class AdminController {

    private final AdminService adminService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/bijoux")
    public String getBijoux(Pageable pageable, Model model) {
       // controllerUtils.addPagination(model, adminService.getBijoux(pageable));
        controllerUtils.addPagination(model,adminService.getBijoux(pageable));
        return Pages.ADMIN_BIJOUX;
    }

    @GetMapping("bijoux/search")
    public String searchBijoux(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchBijoux(request, pageable));
        return Pages.ADMIN_PERFUMES;
    }

    @GetMapping("/users")
    public String getUsers(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getUsers(pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/users/search")
    public String searchUsers(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchUsers(request, pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", adminService.getOrder(orderId));
        return Pages.ORDER;
    }

    @GetMapping("/orders")
    public String getOrders(Pageable pageable, Model model) {
        controllerUtils.addPagination(SearchRequest searchRequest,model, adminService.getOrders(pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/orders/search")
    public String searchOrders(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchOrders(request, pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/bijoux/{bijouxId}")
    public String getBijoux(@PathVariable Long bijouxId, Model model) {
        model.addAttribute("bijoux", adminService.getBijouxById(bijouxId));
        return Pages.ADMIN_EDIT_PERFUME;
    }

    @PostMapping("/edit/bijoux")
    public String editBijoux(@Valid BijouxRequest bijoux, BindingResult bindingResult, Model model,
                              @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "bijoux", bijoux)) {
            return Pages.ADMIN_EDIT_PERFUME;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/bijouxs", adminService.editBijoux(bijoux, file));
    }

    @GetMapping("/add/bijoux")
    public String addBijoux() {
        return Pages.ADMIN_ADD_PERFUME;
    }

    @PostMapping("/add/bijoux")
    public String addBijoux(@Valid BijouxRequest bijoux, BindingResult bindingResult, Model model,
                             @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "bijoux", bijoux)) {
            return Pages.ADMIN_ADD_PERFUME;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/bijouxs", adminService.addBijoux(bijoux, file));
    }

    @GetMapping("/user/{userId}")
    public String getUserById(@PathVariable Long userId, Model model, Pageable pageable) {
        UserInfoResponse userResponse = adminService.getUserById(userId, pageable);
        model.addAttribute("user", userResponse.getUser());
        controllerUtils.addPagination(model, userResponse.getOrders());
        return Pages.ADMIN_USER_DETAIL;
    }
}
