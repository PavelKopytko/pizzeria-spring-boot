package com.example.demo.pizza.service;


import com.example.demo.pizza.core.dto.PizzaInfoDto;
import com.example.demo.pizza.core.entity.PizzaInfo;
import com.example.demo.pizza.dao.IPizzaInfoDao;
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
public class PizzaInfoService implements IPizzaInfoService {

    private IPizzaInfoDao pizzaInfoDao;

    public PizzaInfoService(IPizzaInfoDao dao) {
        this.pizzaInfoDao = dao;
    }


    @Override
    @Transactional
    public PizzaInfo create(PizzaInfoDto item) throws ServiceException, ValidateException, NotUniqServiceException {

        PizzaInfo pizzaInfo;

//        try {
        validate(item);

        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

        PizzaInfo pizzaInfo1 = new PizzaInfo(localDateTime, localDateTime, item.getName(), item.getDescription(), item.getSize());
        pizzaInfo = pizzaInfoDao.save(pizzaInfo1);

//        } catch (IllegalStateException | IllegalArgumentException e) {
//            throw new ValidateException(e.getMessage(), e);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        } catch (NotUniqDaoException e) {
//            throw new NotUniqServiceException(e.getMessage(), e);
//        }

        return pizzaInfo;
    }

    @Override
    public PizzaInfo read(long id) throws IDServiceException, ServiceException {
        PizzaInfo pizzaInfo;

        try {
            pizzaInfo = pizzaInfoDao.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new IDServiceException("pizzaInfo is not found");
        }
//        pizzaInfo = this.pizzaInfoDao.getReferenceById(id);

//        if (pizzaInfo == null) {
//            throw new IDServiceException("Информация не найдена");
//        }

//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        }

        return pizzaInfo;
    }

    @Override
    public List<PizzaInfo> get() throws ServiceException {

        List<PizzaInfo> pizzaInfo;
//        try {
        pizzaInfo = pizzaInfoDao.findAll();
        if (pizzaInfo.isEmpty()) {
            throw new IllegalArgumentException("Нет доступных pizzaInfo");
        }
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        }
        return pizzaInfo;
    }

    @Override
    @Transactional
    public PizzaInfo update(long id, LocalDateTime dtUpdate, PizzaInfoDto item) throws ValidateException, ServiceException, NotUniqServiceException {
        PizzaInfo readed;
        PizzaInfo pizzaInfo;
//        try {
        validate(item);

        readed = pizzaInfoDao.getReferenceById(id);

        if (readed == null) {
            throw new IllegalArgumentException("Инфо не найдено");
        }

        if (!readed.getDtUpdate().isEqual(dtUpdate)) {
            throw new IllegalArgumentException("К сожалению инфо уже было отредактировано кем-то другим");
        }

//            readed.setDtUpdate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS)); // Не нужна из-за @Version
        readed.setName(item.getName());
        readed.setDescription(item.getDescription());
        readed.setSize(item.getSize());

        pizzaInfo = pizzaInfoDao.save(readed);

//        } catch (IllegalStateException | IllegalArgumentException e) {
//            throw new ValidateException(e.getMessage(), e);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        } catch (NotUniqDaoException e) {
//            throw new NotUniqServiceException(e.getMessage(), e);
//        }

        return pizzaInfo;
    }

    @Override
    @Transactional
    public void delete(long id, LocalDateTime dtUpdate) throws ServiceException {

        try {
            pizzaInfoDao.delete(read(id));
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
        } catch (IDServiceException e) {
            throw new RuntimeException(e);
        }

    }

    public void validate(PizzaInfoDto item) {
        if (item == null) {
            throw new IllegalStateException("Вы не передали инфо");
        }
        if (item.getName() == null || item.getName().isBlank()) {
            throw new IllegalArgumentException("Вы не заполнили название пиццы");
        }
        if (item.getDescription() == null || item.getDescription().isBlank()) {
            throw new IllegalArgumentException("Вы не заполнили описание аиццы");
        }
        if (item.getSize() <= 0) {
            throw new IllegalArgumentException("Вы не заполнили размер пиццы");
        }
    }
}
