import com.hr.commonutils.Payment;
import com.hr.springcloud.PaymentMain8001;
import com.hr.springcloud.dao.PaymentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = PaymentMain8001.class)
@RunWith(SpringRunner.class)
public class PaymentTest {

    @Autowired
    private PaymentDao paymentDao;

    @Test
    public void save() {
        Payment payment = new Payment();
        payment.setSerial("测试");
        int i = paymentDao.create(payment);
        System.out.println(i);
    }


    @Test
    public void getPaymentById() {
        Payment payment = paymentDao.getPaymentById(1L);
        System.out.println(payment);
    }
}
