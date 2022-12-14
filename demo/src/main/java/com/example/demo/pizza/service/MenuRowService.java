package com.example.demo.pizza.service;


import com.example.demo.pizza.core.dto.MenuRowDto;
import com.example.demo.pizza.core.entity.MenuRow;
import com.example.demo.pizza.core.entity.api.IPizzaInfo;
import com.example.demo.pizza.dao.IMenuRowDao;
import com.example.demo.pizza.service.api.IMenuRowService;
import com.example.demo.pizza.service.api.IPizzaInfoService;
import com.example.demo.pizza.service.exception.IDServiceException;
import com.example.demo.pizza.service.exception.NotUniqServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import com.example.demo.pizza.service.exception.ValidateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional(readOnly = true)
public class MenuRowService implements IMenuRowService {

    private IMenuRowDao menuRowDao;
    private IPizzaInfoService pizzaInfoService;


    public MenuRowService(IMenuRowDao menuRowDao, IPizzaInfoService pizzaInfoService) {
        this.menuRowDao = menuRowDao;
        this.pizzaInfoService = pizzaInfoService;
    }

    @Override
    @Transactional
    public MenuRow create(MenuRowDto item) throws ValidateException, ServiceException, NotUniqServiceException {

        MenuRow menuRow;

        try {
            validate(item);

            IPizzaInfo pizzaInfo = pizzaInfoService.read(item.getInfoId());
            LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
            MenuRow row = new MenuRow(
                    localDateTime,
                    localDateTime,
                    pizzaInfo,
                    item.getPrice()
            );

            menuRow = menuRowDao.save(row);


        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new ValidateException(e.getMessage(), e);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        } catch (NotUniqDaoException e) {
//            throw new NotUniqServiceException(e.getMessage(), e);
        } catch (IDServiceException e) {
            throw new RuntimeException(e);
        }

        return menuRow;
    }

    @Override
    public MenuRow read(long id) throws IDServiceException, ServiceException {
        MenuRow menuRow;

        try {

            menuRow = menuRowDao.findById(id).orElseThrow();

        } catch (NoSuchElementException e) {
            throw new IDServiceException("pizzaInfo is not found");
        }

        return menuRow;

    }

    @Override
    public List<MenuRow> get() throws ServiceException {

        List<MenuRow> menuRows;

        menuRows = menuRowDao.findAll();

        if (menuRows.isEmpty()) {
            throw new IllegalArgumentException("Нет доступных menuRow");
        }
        return menuRows;
    }

    @Override
    @Transactional
    public MenuRow update(long id, LocalDateTime dtUpdate, MenuRowDto item) throws ValidateException, ServiceException, NotUniqServiceException {

        MenuRow readed;

        MenuRow menuRow;
        try {
            validate(item);

            readed = menuRowDao.getReferenceById(id);

            if (readed == null) {
                throw new IllegalArgumentException("Строка меню не найдена");
            }
            if (!readed.getDtUpdate().isEqual(dtUpdate)) {
                throw new IllegalArgumentException("К сожалению инфо уже было отредактировано кем-то другим");
            }

            IPizzaInfo pizzaInfo = pizzaInfoService.read(item.getInfoId());


            readed.setInfo(pizzaInfo);
            readed.setPrice(item.getPrice());

            menuRow = menuRowDao.save(readed);


        } catch (IllegalStateException | IllegalArgumentException | IDServiceException e) {
            throw new ValidateException(e.getMessage(), e);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        } catch (NotUniqDaoException e) {
//            throw new NotUniqServiceException(e.getMessage(), e);
        }

        return menuRow;
    }

    @Override
    @Transactional
    public void delete(long id, LocalDateTime dtUpdate) throws ServiceException {

        try {
            menuRowDao.delete(read(id));
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
        } catch (IDServiceException e) {
            throw new RuntimeException(e);
        }

    }

    public void validate(MenuRowDto item) {
        if (item == null) {
            throw new IllegalStateException("Вы не передали строку меню");
        }
        if (item.getInfoId() <= 0) {
            throw new IllegalArgumentException("Вы не заполнили инфо");
        }
        if (item.getPrice() <= 0) {
            throw new IllegalArgumentException("Вы не заполнили цену");
        }
        if (item.getMenuId() <= 0) {
            throw new IllegalArgumentException("Вы не указали меню");
        }
    }
}
