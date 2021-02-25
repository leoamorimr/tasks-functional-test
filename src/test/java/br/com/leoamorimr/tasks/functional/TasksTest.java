package br.com.leoamorimr.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {
//        WebDriver driver = new ChromeDriver(options);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.3.31:4444/wd/hub"), cap);
        driver.navigate().to("http://192.168.3.31:8001/tasks");
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();
        try {
//        Incluindo estratégia de timeout para verificar a iteração
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        Clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

//        Escrever a Task
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

//        Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

//        Clicar em Save
            driver.findElement(By.id("saveButton")).click();

//        Validar mensagem com sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);
        } finally {
//        Incluindo estratégia de fechamento do Browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try {
//        Incluindo estratégia de timeout para verificar a iteração
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        Clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

//        Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

//        Clicar em Save
            driver.findElement(By.id("saveButton")).click();

//        Validar mensagem com sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);
        } finally {
//        Incluindo estratégia de fechamento do Browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();
        try {
//        Incluindo estratégia de timeout para verificar a iteração
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        Clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

//        Escrever a Task
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

//        Clicar em Save
            driver.findElement(By.id("saveButton")).click();

//        Validar mensagem com sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);
        } finally {
//        Incluindo estratégia de fechamento do Browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();
        try {
//        Incluindo estratégia de timeout para verificar a iteração
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        Clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

//        Escrever a Task
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

//        Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("01/01/1999");

//        Clicar em Save
            driver.findElement(By.id("saveButton")).click();

//        Validar mensagem com sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);
        } finally {
//        Incluindo estratégia de fechamento do Browser
            driver.quit();
        }
    }

    @Test
    public void deveRemoverTarefaComSucesso() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();
        try {
            //Inserindo a tarefa
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

//        Removendo a tarefa

//        Clicar em Remove
            driver.findElement(By.xpath("//a[@Class='btn btn-outline-danger btn-sm']")).click();

//        Validar mensagem com sucesso
            message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);
        } finally {
            driver.quit();
        }
    }

}
