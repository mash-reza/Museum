package sample.database;

import sample.model.dto.*;
import sample.model.pojo.ArtObject;
import sample.model.pojo.Artist;
import sample.model.pojo.Collection;
import sample.model.pojo.Exhibition;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=museum;integratedSecurity=true";
    private static DatabaseUtil object;
    private Connection connection;
    private Statement statement;

    public static synchronized DatabaseUtil getObject() {
        if (object == null) {
            object = new DatabaseUtil();
            object.connect();
            return object;
        }
        return object;
    }

    private void connect() {
        try {
            System.out.print("Connecting to SQL Server ... ");
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();
            System.out.println("Done.");
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public List<ArtObjectArtistView> view1() throws SQLException {
        List<ArtObjectArtistView> data = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT [Art_Object_Title]\n" +
                "      ,[Artist_Name]\n" +
                "      ,[Art_Object_Year]\n" +
                "      ,[Art_Object_Description]\n" +
                "      ,[Art_Object_Country_of_origin]\n" +
                "      ,[Artist_Date_born]\n" +
                "      ,[Artist_Date_died]\n" +
                "      ,[Artist_Epoch]\n" +
                "  FROM [dbo].[getAllArtObjectAndArtistView]\n" +
                "GO");
        while (set.next()) {
//            System.out.println(set);
            ArtObjectArtistView view = new ArtObjectArtistView();
            view.setName(set.getString("Artist_Name"));
            view.setDateBorn(set.getDate("Artist_Date_born"));
            view.setDateBorn(set.getDate("Artist_Date_died"));
            view.setEpoch(set.getString("Artist_Epoch"));
            view.setTitle(set.getString("Art_Object_Title"));
            view.setYear(set.getDate("Art_Object_Year"));
            view.setDescription(set.getString("Art_Object_Description"));
            view.setCountry(set.getString("Art_Object_Country_of_origin"));
            data.add(view);
        }
        return data;
    }

    public List<ArtObjectBorrowed> view2() throws SQLException {
        List<ArtObjectBorrowed> data = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT [Art_Object_Title]\n" +
                "      ,[Date_to_return]\n" +
                "      ,[Date_borrowed]\n" +
                "  FROM [dbo].[getAllBorrowedView]\n" +
                "GO");
        while (set.next()) {
//            System.out.println(set);
            ArtObjectBorrowed view = new ArtObjectBorrowed();
            view.setTitle(set.getString("Art_Object_Title"));
            view.setDateBorrowed(set.getDate("Date_borrowed"));
            view.setDateToReturn(set.getDate("Date_to_return"));
            data.add(view);
        }
//        System.out.println(data);
        return data;
    }

    public List<ArtObjectPainting> view3() throws SQLException {
        List<ArtObjectPainting> data = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT [Art_Object_Title]\n" +
                "      ,[Painting_Paint_type]\n" +
                "      ,[Art_Object_Style]\n" +
                "  FROM [dbo].[getAllPaintingsView]\n" +
                "GO");
        while (set.next()) {
            ArtObjectPainting view = new ArtObjectPainting();
            view.setTitle(set.getString("Art_Object_Title"));
            view.setPaintType(set.getString("Art_Object_Style"));
            view.setStyle(set.getString("Painting_Paint_type"));
            data.add(view);
        }
        return data;
    }

    public List<ArtObjectPermanent> view4() throws SQLException {
        List<ArtObjectPermanent> data = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT [Art_Object_Title]\n" +
                "      ,[Permanent_Cost]\n" +
                "      ,[Art_Object_Year]\n" +
                "      ,[Permanent_Date_acquired]\n" +
                "  FROM [dbo].[getAllPermanentView]\n" +
                "GO");
        while (set.next()) {
            ArtObjectPermanent view = new ArtObjectPermanent();
            view.setTitle(set.getString("Art_Object_Title"));
            view.setCost(set.getLong("Permanent_Cost"));
            view.setDateAcquired(set.getDate("Permanent_Date_acquired"));
            view.setYear(set.getString("Art_Object_Year"));
            data.add(view);
        }
        return data;
    }

    public List<ArtObjectSculpture> view5() throws SQLException {
        List<ArtObjectSculpture> data = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT [Art_Object_Title]\n" +
                "      ,[Art_Object_Year]\n" +
                "      ,[Art_Object_Description]\n" +
                "      ,[Sculpture_Height]\n" +
                "      ,[Sculpture_Weight]\n" +
                "      ,[Art_Object_Country_of_origin]\n" +
                "      ,[Sculpture_type]\n" +
                "  FROM [dbo].[getAllScupltureView]\n" +
                "GO");
        while (set.next()) {
            ArtObjectSculpture view = new ArtObjectSculpture();
            view.setTitle(set.getString("Art_Object_Title"));
            view.setCountry(set.getString("Art_Object_Country_of_origin"));
            view.setHeight(set.getFloat("Sculpture_Height"));
            view.setWeight(set.getFloat("Sculpture_Weight"));
            view.setYear(set.getDate("Art_Object_Year"));
            view.setType(set.getString("Sculpture_type"));
            view.setDescription(set.getString("Art_Object_Description"));
            data.add(view);
        }
        System.out.println("view 5 called");
        return data;
    }

    public String storeProcedure1() throws SQLException {
        CallableStatement callableStatement = connection.prepareCall("{call calculateMoneySpent}");
        callableStatement.execute();
        ResultSet set = callableStatement.getResultSet();
        if (set.next()) {
            return String.valueOf(set.getInt("money_spent"));
        }
        return "";
    }

    public List<Exhibition> storeProcedure2() throws ParseException, SQLException {
        CallableStatement statement = connection.prepareCall("exec getExhibitionByDateRange ?,?");
        statement.setDate(1, new Date(new SimpleDateFormat("yyyy/MM/dd").parse("1301/1/1").getTime()));
        statement.setDate(2, new Date(new SimpleDateFormat("yyyy/MM/dd").parse("1390/1/1").getTime()));
        boolean isExecuted = statement.execute();
        if (isExecuted) {
            List<Exhibition> exhibitions = new ArrayList<>();
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                Exhibition exhibition = new Exhibition();
                exhibition.setId(set.getInt("Exhibition_Id"));
                exhibition.setName(set.getString("Exhibition_Name"));
                exhibition.setStart(set.getDate("Exhibition_Start_date"));
                exhibition.setEnd(set.getDate("Exhibition_End_date"));
                exhibitions.add(exhibition);
            }
            return exhibitions;
        }
        return null;
    }

    public List<ArtObject> storeProcedure3() throws SQLException {
        CallableStatement statement = connection.prepareCall("{call getOnDisplayArtObjects}");
        if (statement.execute()) {
            List<ArtObject> artObjects = new ArrayList<>();
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                ArtObject artObject = new ArtObject();
                artObject.setTitle(set.getString("Art_Object_Title"));
                artObject.setCountry(set.getString("Art_Object_Country_of_origin"));
                artObject.setDescription(set.getString("Art_Object_Description"));
                artObject.setYear(set.getDate("Art_Object_Year"));
                artObject.setCategory(set.getString("Art_Object_Category"));
                artObject.setEpoch(set.getString("Art_Object_Epoch"));
                artObject.setStatus(set.getString("Art_Object_Status"));
                artObject.setStyle(set.getString("Art_Object_Style"));
                artObject.setType(set.getString("Art_Object_Type"));
                artObjects.add(artObject);
            }
            return artObjects;
        }
        return null;
    }

    public List storeProcedure4(String type) throws SQLException {
        CallableStatement statement = connection.prepareCall("exec getArtObjectByType ?");
        statement.setString(1, type);
        switch (type) {
            case "painting":
                if (statement.execute()) {
                    ResultSet set = statement.getResultSet();
                    List<ArtObjectPainting> artObjectPaintings = new ArrayList<>();
                    while (set.next()) {
                        ArtObjectPainting artObjectPainting = new ArtObjectPainting();
                        artObjectPainting.setTitle(set.getString("Art_Object_Title"));
                        artObjectPainting.setStyle(set.getString("Art_Object_Style"));
                        artObjectPainting.setPaintType(set.getString("Painting_Paint_type"));
                        artObjectPainting.setCategory(set.getString("Art_Object_Category"));
                        artObjectPainting.setCountry(set.getString("Art_Object_Country_of_origin"));
                        artObjectPainting.setDescription(set.getString("Art_Object_Description"));
                        artObjectPainting.setEpoch(set.getString("Art_Object_Epoch"));
                        artObjectPainting.setMaterial(set.getString("Painting_Material"));
                        artObjectPainting.setStatus(set.getString("Art_Object_Status"));
                        artObjectPainting.setYear(set.getDate("Art_Object_Year"));
                        artObjectPainting.setMaterial(set.getString("Painting_Material"));
                        artObjectPaintings.add(artObjectPainting);
                    }
                    return artObjectPaintings;
                }
                break;
            case "sculpture":
                if (statement.execute()) {
                    ResultSet set = statement.getResultSet();
                    List<ArtObjectSculpture> artObjectPaintings = new ArrayList<>();
                    while (set.next()) {
                        ArtObjectSculpture artObjectSculpture = new ArtObjectSculpture();
                        artObjectSculpture.setTitle(set.getString("Art_Object_Title"));
                        artObjectSculpture.setStyle(set.getString("Art_Object_Style"));
                        artObjectSculpture.setCategory(set.getString("Art_Object_Category"));
                        artObjectSculpture.setCountry(set.getString("Art_Object_Country_of_origin"));
                        artObjectSculpture.setDescription(set.getString("Art_Object_Description"));
                        artObjectSculpture.setEpoch(set.getString("Art_Object_Epoch"));
                        artObjectSculpture.setStatus(set.getString("Art_Object_Status"));
                        artObjectSculpture.setYear(set.getDate("Art_Object_Year"));
                        artObjectSculpture.setSculptureType(set.getString("Sculpture_type"));
                        artObjectSculpture.setHeight(set.getFloat("Sculpture_Height"));
                        artObjectSculpture.setWeight(set.getFloat("Sculpture_Weight"));
                        artObjectPaintings.add(artObjectSculpture);
                    }
                    return artObjectPaintings;
                }
                break;
        }
        return null;
    }

    public List<ArtObject> tableFunction1(String country) throws SQLException {
        ResultSet set = statement.executeQuery("SELECT * FROM [dbo].[getArtObjectsByCountry] ('" + country + "')\n" +
                "GO");
        List<ArtObject> artObjects = new ArrayList<>();
        while (set.next()) {
            ArtObject artObject = new ArtObject();
            artObject.setTitle(set.getString("Title"));
            artObject.setDescription(set.getString("Description"));
            artObject.setYear(set.getDate("Year"));
            artObjects.add(artObject);
        }
        return artObjects;
    }

    public List<Collection> tableFunction2(String name) throws SQLException {
        ResultSet set = statement.executeQuery("SELECT * FROM [dbo].[getCollection] ('" + name + "')\n" +
                "GO");
        List<Collection> collections = new ArrayList<>();
        while (set.next()) {
            Collection collection = new Collection();
            collection.setName(set.getString("Collection_Name"));
            collection.setDescription(set.getString("Collection_Description"));
            collection.setType(set.getString("Collection_Type"));
            collection.setPhone(set.getString("Collection_Phone"));
            collection.setContactPerson(set.getString("Collection_Contact_person"));
            collection.setAddress(set.getString("Collection_Address"));
            collections.add(collection);
        }
        return collections;
    }

    public String scalarFunction1(String name) throws SQLException {
        ResultSet set = statement.executeQuery("SELECT [dbo].[calculateArtistAge] ('" + name + "') as age");
        if (set.next()) {
            return set.getString("age");
        }
        return null;
    }

    public String scalarFunction2(String title) throws SQLException {
        ResultSet set = statement.executeQuery("SELECT [dbo].[calculateHowLongArtObjectWasBorrowed] ('" + title + "') as time");
        if (set.next()) {
            return set.getString("time");
        }
        return null;
    }

    public boolean backup(String path) {
        try {
            statement.execute("BACKUP DATABASE museum TO DISK = '" + path + "\\museum.bak';");
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean enableAutoBackup(int frequency, String date) {
        try {
            statement.execute(
                    "BEGIN TRANSACTION\n" +
                            "DECLARE @ReturnCode INT\n" +
                            "SELECT @ReturnCode = 0\n" +
                            "IF NOT EXISTS (SELECT name FROM msdb.dbo.syscategories WHERE name=N'[Uncategorized (Local)]' AND category_class=1)\n" +
                            "BEGIN\n" +
                            "EXEC @ReturnCode = msdb.dbo.sp_add_category @class=N'JOB', @type=N'LOCAL', @name=N'[Uncategorized (Local)]'\n" +
                            "IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback\n" +
                            "\n" +
                            "END\n" +
                            "\n" +
                            "DECLARE @jobId BINARY(16)\n" +
                            "EXEC @ReturnCode =  msdb.dbo.sp_add_job @job_name=N'my_backup', \n" +
                            "\t\t@enabled=1, \n" +
                            "\t\t@notify_level_eventlog=0, \n" +
                            "\t\t@notify_level_email=0, \n" +
                            "\t\t@notify_level_netsend=0, \n" +
                            "\t\t@notify_level_page=0, \n" +
                            "\t\t@delete_level=0, \n" +
                            "\t\t@description=N'No description available.', \n" +
                            "\t\t@category_name=N'[Uncategorized (Local)]', \n" +
                            "\t\t@owner_login_name=N'DESKTOP-A9S75LD\\Festive', @job_id = @jobId OUTPUT\n" +
                            "IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback\n" +
                            "EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'command', \n" +
                            "\t\t@step_id=1, \n" +
                            "\t\t@cmdexec_success_code=0, \n" +
                            "\t\t@on_success_action=1, \n" +
                            "\t\t@on_success_step_id=0, \n" +
                            "\t\t@on_fail_action=2, \n" +
                            "\t\t@on_fail_step_id=0, \n" +
                            "\t\t@retry_attempts=0, \n" +
                            "\t\t@retry_interval=0, \n" +
                            "\t\t@os_run_priority=0, @subsystem=N'TSQL', \n" +
                            "\t\t@command=N'BACKUP DATABASE museum TO DISK = ''\"+path+\"\\\\museum.bak'';', \n" +
                            "\t\t@database_name=N'master', \n" +
                            "\t\t@flags=0\n" +
                            "IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback\n" +
                            "EXEC @ReturnCode = msdb.dbo.sp_update_job @job_id = @jobId, @start_step_id = 1\n" +
                            "IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback\n" +
                            "EXEC @ReturnCode = msdb.dbo.sp_add_jobschedule @job_id=@jobId, @name=N'schedule', \n" +
                            "\t\t@enabled=1, \n" +
                            "\t\t@freq_type=" + frequency + ", \n" +
                            "\t\t@freq_interval=1, \n" +
                            "\t\t@freq_subday_type=1, \n" +
                            "\t\t@freq_subday_interval=0, \n" +
                            "\t\t@freq_relative_interval=0, \n" +
                            "\t\t@freq_recurrence_factor=1, \n" +
                            "\t\t@active_start_date=" + date + ", \n" +
                            "\t\t@active_end_date=99991231, \n" +
                            "\t\t@active_start_time=0, \n" +
                            "\t\t@active_end_time=235959, \n" +
                            "\t\t@schedule_uid=N'87f959cc-b298-4414-9de4-16107ed6d83d'\n" +
                            "IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback\n" +
                            "EXEC @ReturnCode = msdb.dbo.sp_add_jobserver @job_id = @jobId, @server_name = N'(local)'\n" +
                            "IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback\n" +
                            "COMMIT TRANSACTION\n" +
                            "GOTO EndSave\n" +
                            "QuitWithRollback:\n" +
                            "    IF (@@TRANCOUNT > 0) ROLLBACK TRANSACTION\n" +
                            "EndSave:\n");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean disableAutoBackup() {
        try {
            statement.execute(
                    "DECLARE @jobId binary(16)\n" +
                            "\n" +
                            "SELECT @jobId = job_id FROM msdb.dbo.sysjobs WHERE (name = N'my_backup')\n" +
                            "IF (@jobId IS NOT NULL)\n" +
                            "BEGIN\n" +
                            "    EXEC msdb.dbo.sp_delete_job @jobId, @delete_unused_schedule=1\n" +
                            "END");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addUser(String username, String password, boolean policy, boolean expiration) {
        try {
            String policyValue;
            if (policy)
                policyValue = "ON";
            else policyValue = "OFF";
            String expirationValue;
            if (expiration)
                expirationValue = "ON";
            else expirationValue = "OFF";
            statement.execute(
                    "CREATE LOGIN [" + username + "] WITH PASSWORD=N'" + password + "', DEFAULT_DATABASE=[museum], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=" + expirationValue + ", CHECK_POLICY=" + policyValue + "\n" +
                            "CREATE USER ["+username+"] FOR LOGIN ["+username+"] WITH DEFAULT_SCHEMA=[dbo]"
                    );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
