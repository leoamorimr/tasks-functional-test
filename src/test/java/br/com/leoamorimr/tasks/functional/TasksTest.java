package br.com.leoamorimr.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() {
//        Para colocar o Brave como navegador ao invés do Chrome deve-se incluir o diretório do binário.
        ChromeOptions options = new ChromeOptions().setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.navigate().to("http://localhost:8001/tasks");
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() {

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
    public void naoDeveSalvarTarefaSemDescricao() {
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
    public void naoDeveSalvarTarefaSemData() {

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
    public void naoDeveSalvarTarefaComDataPassada() {

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

}
