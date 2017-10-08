package guidemo;

import java.awt.CardLayout;
import java.io.File;
import javax.swing.JFileChooser;
import utils.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GUIDemo extends javax.swing.JFrame {

private ArrayList<HashMap<String,String>> allMoviesal;

private ArrayList<String> movieTitleal = new ArrayList<>();
private ArrayList<String> foldersListal = new ArrayList<>();
private ArrayList<String> castBasedDetails = new ArrayList<>();
private ArrayList<String> genreBasedDetails = new ArrayList<>();

private HashMap<String,String> genrehm = new HashMap<>();
private HashMap<String,String> casthm = new HashMap<>();
/*
    Program will throw error if the cast of a particular movie returns as ""(empty).
*/
private HashMap<String,String> overviewhm = new HashMap<>();
private HashMap<String,String> posterPathhm = new HashMap<>();
private HashMap<String,String> idhm = new HashMap<>();

private static String imagePath;
private String temp;

    public GUIDemo() 
	{         
        DbUtils.initDb();
        if(Objects.equals(DbUtils.getClassName(), "org.apache.derby.jdbc.ClientDriver"))
        {
            if(!DbUtils.checkTable("MOVIES")){
                DbUtils.createMovieDb();
            }
            if(!DbUtils.checkTable("LIBRARYFOLDER")){
                DbUtils.createMovieLibraryFolderDb();
            }
        }
        else if(Objects.equals(DbUtils.getClassName(), "com.mysql.jdbc.Driver"))
        {
            if(!DbUtils.checkTable("movies")){
                DbUtils.createMovieDb();
            }
            if(!DbUtils.checkTable("libraryfolder")){
                DbUtils.createMovieLibraryFolderDb();
            }       
        }
        allMoviesal = DbUtils.getMovieDetails();
        getMovieData(); 
        imagePath = UrlUtils.getBaseImagePath();
        initComponents();    
    }
    
    public GUIDemo(String imagePath,String className,String dburl,String password,String user) 
	{  
        UrlUtils.setBaseImagePath(imagePath);
        DbUtils.setClassName(className);
        DbUtils.setDatabaseurl(dburl);
        DbUtils.setPassword(password);
        DbUtils.setUser(user);
        DbUtils.initDb();
        if(Objects.equals(DbUtils.getClassName(), "org.apache.derby.jdbc.ClientDriver"))
        {
            if(!DbUtils.checkTable("MOVIES")){
                DbUtils.createMovieDb();
            }
            if(!DbUtils.checkTable("LIBRARYFOLDER")){
                DbUtils.createMovieLibraryFolderDb();
            }
        }
        else if(Objects.equals(DbUtils.getClassName(), "com.mysql.jdbc.Driver"))
        {
            if(!DbUtils.checkTable("movies")){
                DbUtils.createMovieDb();
            }
            if(!DbUtils.checkTable("libraryfolder")){
                DbUtils.createMovieLibraryFolderDb();
            }       
        }   
        allMoviesal = DbUtils.getMovieDetails();
        getMovieData(); 
        imagePath = UrlUtils.getBaseImagePath();
        initComponents();   
    }
     
    public void getMovieData()
    {
        movieTitleal.clear();
        foldersListal.clear();
        genrehm.clear();
        idhm.clear();      
        casthm.clear();
        overviewhm.clear();
        posterPathhm.clear();
        for(int i=0; i<allMoviesal.size(); i++)
		{
			temp = allMoviesal.get(i).get("Title").replace("''", "'");
			movieTitleal.add(temp);
			foldersListal.add(allMoviesal.get(i).get("FolderName"));
			genrehm.put(temp, allMoviesal.get(i).get("Genres"));
			idhm.put(temp, allMoviesal.get(i).get("Id"));
			casthm.put(temp, allMoviesal.get(i).get("Cast"));
			posterPathhm.put(temp, allMoviesal.get(i).get("PosterPath"));
			overviewhm.put(temp, allMoviesal.get(i).get("Overview"));					             
       }
        Set<String> hs = new HashSet<>();
        hs.addAll(movieTitleal);
        movieTitleal.clear();
        movieTitleal.addAll(hs);
        Collections.sort(movieTitleal);
        hs.clear();
            
        hs.addAll(foldersListal);
        foldersListal.clear();
        foldersListal.addAll(hs);
        Collections.sort(foldersListal);           
    } 
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        basePanel = new javax.swing.JPanel();
        startPanel = new javax.swing.JPanel();
        addFoldersButton = new javax.swing.JButton();
        movieLibraryButton = new javax.swing.JButton();
        updateLibraryButton = new javax.swing.JButton();
        addFoldersPanel = new javax.swing.JPanel();
        BrowseTextField = new javax.swing.JTextField();
        BrowseButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();
        RemoveButton = new javax.swing.JButton();
        OKButton = new javax.swing.JButton();
        addedFoldersScrollPane = new javax.swing.JScrollPane();
        addedFoldersList = new javax.swing.JList<>();
        movieLibraryPanel = new javax.swing.JPanel();
        movieTitleScrollPane = new javax.swing.JScrollPane();
        showMovieList = new javax.swing.JList<>();
        imageLabel = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        castLabel = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        overviewScrollPane = new javax.swing.JScrollPane();
        overviewTextArea = new javax.swing.JTextArea();
        homeLibraryButton = new javax.swing.JButton();
        castScrollPane = new javax.swing.JScrollPane();
        castList = new javax.swing.JList<>();
        resetButton = new javax.swing.JButton();
        genreScrollPane = new javax.swing.JScrollPane();
        genreList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie Database");
        setMinimumSize(new java.awt.Dimension(900, 620));
        setResizable(false);

        basePanel.setPreferredSize(new java.awt.Dimension(820, 635));
        basePanel.setLayout(new java.awt.CardLayout());

        startPanel.setPreferredSize(new java.awt.Dimension(820, 635));

        addFoldersButton.setText("Add Folders");
        addFoldersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFoldersButtonActionPerformed(evt);
            }
        });

        movieLibraryButton.setText("Open Movie Library");
        movieLibraryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieLibraryButtonActionPerformed(evt);
            }
        });

        updateLibraryButton.setText("Update Library");
        updateLibraryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateLibraryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout startPanelLayout = new javax.swing.GroupLayout(startPanel);
        startPanel.setLayout(startPanelLayout);
        startPanelLayout.setHorizontalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startPanelLayout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addGroup(startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addFoldersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(224, Short.MAX_VALUE))
        );
        startPanelLayout.setVerticalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(addFoldersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                .addComponent(movieLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184)
                .addComponent(updateLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        basePanel.add(startPanel, "card2");

        addFoldersPanel.setMinimumSize(new java.awt.Dimension(900, 580));
        addFoldersPanel.setPreferredSize(new java.awt.Dimension(820, 635));

        BrowseButton.setText("Browse");
        BrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseButtonActionPerformed(evt);
            }
        });

        AddButton.setText("Add");
        AddButton.setPreferredSize(new java.awt.Dimension(67, 23));
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        RemoveButton.setText("Remove");
        RemoveButton.setPreferredSize(new java.awt.Dimension(67, 23));
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });

        OKButton.setText("OK");
        OKButton.setPreferredSize(new java.awt.Dimension(67, 23));
        OKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });

        addedFoldersList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = DbUtils.getLibraryFolders();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        addedFoldersScrollPane.setViewportView(addedFoldersList);

        javax.swing.GroupLayout addFoldersPanelLayout = new javax.swing.GroupLayout(addFoldersPanel);
        addFoldersPanel.setLayout(addFoldersPanelLayout);
        addFoldersPanelLayout.setHorizontalGroup(
            addFoldersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addFoldersPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(addFoldersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addedFoldersScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addComponent(BrowseTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addFoldersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowseButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RemoveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OKButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        addFoldersPanelLayout.setVerticalGroup(
            addFoldersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFoldersPanelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(addFoldersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BrowseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(BrowseTextField))
                .addGap(40, 40, 40)
                .addGroup(addFoldersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addFoldersPanelLayout.createSequentialGroup()
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(RemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                        .addComponent(OKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addedFoldersScrollPane))
                .addGap(39, 39, 39))
        );

        basePanel.add(addFoldersPanel, "card3");

        movieLibraryPanel.setPreferredSize(new java.awt.Dimension(820, 635));

        showMovieList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = movieTitleal.toArray(new String[movieTitleal.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        showMovieList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                showMovieListValueChanged(evt);
            }
        });
        movieTitleScrollPane.setViewportView(showMovieList);

        imageLabel.setToolTipText("");

        titleTextField.setFont(new java.awt.Font("Tahoma", 1, 12));
        titleTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        if(!movieTitleal.isEmpty()){
            titleTextField.setText(movieTitleal.get(0));
        }

        castLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        castLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        castLabel.setText("Cast");

        genreLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        genreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        genreLabel.setText("Genre");

        overviewTextArea.setEditable(false);
        overviewTextArea.setBackground(new java.awt.Color(240, 240, 240));
        overviewTextArea.setColumns(20);
        overviewTextArea.setLineWrap(true);
        overviewTextArea.setWrapStyleWord(true);
        overviewTextArea.setRows(5);
        overviewScrollPane.setViewportView(overviewTextArea);
        overviewTextArea.setText(Objects.equals(titleTextField.getText(), "")?"":
            overviewhm.get(titleTextField.getText()).replace(", ", "\n"));

        homeLibraryButton.setText("Home");
        homeLibraryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeLibraryButtonActionPerformed(evt);
            }
        });

        castList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"","","","",""};

            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        castList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                castListValueChanged(evt);
            }
        });
        castScrollPane.setViewportView(castList);

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        genreList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "", "", "", "", "" };
            //String[] strings = genrehm.get(titleTextField.getText()).replace(", ", "\n").split("[\\r\\n]+");
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        genreList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                genreListValueChanged(evt);
            }
        });
        genreScrollPane.setViewportView(genreList);

        javax.swing.GroupLayout movieLibraryPanelLayout = new javax.swing.GroupLayout(movieLibraryPanel);
        movieLibraryPanel.setLayout(movieLibraryPanelLayout);
        movieLibraryPanelLayout.setHorizontalGroup(
            movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieLibraryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(movieTitleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(movieLibraryPanelLayout.createSequentialGroup()
                        .addComponent(homeLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(37, 37, 37)
                .addGroup(movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(movieLibraryPanelLayout.createSequentialGroup()
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(castLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(castScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(genreScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(42, 42, 42))
                    .addComponent(titleTextField)
                    .addComponent(overviewScrollPane))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        movieLibraryPanelLayout.setVerticalGroup(
            movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieLibraryPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(movieLibraryPanelLayout.createSequentialGroup()
                        .addComponent(movieTitleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(homeLibraryButton, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(movieLibraryPanelLayout.createSequentialGroup()
                        .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(movieLibraryPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(movieLibraryPanelLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(castLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(castScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(movieLibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(genreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(genreScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(13, 13, 13)
                        .addComponent(overviewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        if(!Objects.equals(titleTextField.getText(), ""))
        {
            imageLabel.setIcon(new ImageIcon("F:\\Academic\\JAVA\\moviedDB Data\\" + idhm.get(titleTextField.getText()) + ".jpg"));
            }

            basePanel.add(movieLibraryPanel, "card4");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(basePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(basePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void BrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseButtonActionPerformed
       JFileChooser chooser = new JFileChooser(""); 
       chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       int returnVal = chooser.showOpenDialog(this);
       File file = null;
       if (returnVal == JFileChooser.APPROVE_OPTION)
	   {
           file = chooser.getSelectedFile();
           BrowseTextField.setText(file.getAbsolutePath());
       }      
    }//GEN-LAST:event_BrowseButtonActionPerformed

    private void addFoldersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFoldersButtonActionPerformed
        CardLayout cl = (CardLayout)(basePanel.getLayout());
        cl.show(basePanel, "card3");
    }//GEN-LAST:event_addFoldersButtonActionPerformed

    private void OKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonActionPerformed
        CardLayout cl = (CardLayout)(basePanel.getLayout());
        cl.show(basePanel, "card2");
    }//GEN-LAST:event_OKButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        String path = BrowseTextField.getText();      
        if(!Objects.equals(path, ""))
        {
            ArrayList <String> movies = FileUtils.getMovieNames(path);
            for(Object o:movies)
			{
				HashMap<String,String> hm = UrlUtils.getMovieDetailsByName(o.toString());
				if(hm != null) 
				{
                    hm.put("FolderBasePath", path);
					DbUtils.storeInMovieDb(hm);                       
				}
            }
            DbUtils.storeInMovieLibraryFolderDb(path);
            allMoviesal = DbUtils.getMovieDetails();
            getMovieData();
            addedFoldersList.setListData(DbUtils.getLibraryFolders());
            BrowseTextField.setText("");
            JOptionPane.showMessageDialog(null, "The Folders are Added");         
    }                   
    }//GEN-LAST:event_AddButtonActionPerformed

    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveButtonActionPerformed
        if(addedFoldersList.getSelectedIndex() != -1)
		{
             temp = addedFoldersList.getSelectedValue();     
             if(!Objects.equals(temp, ""))
             {  
                DbUtils.deleteLibraryFolders(temp);
                DbUtils.deleteMoviesByFolderBasePath(temp);
                allMoviesal = DbUtils.getMovieDetails();
                getMovieData();
                addedFoldersList.setListData(DbUtils.getLibraryFolders());
                JOptionPane.showMessageDialog(null, "The Folders are Removed");
             }
        }
    }//GEN-LAST:event_RemoveButtonActionPerformed

    private void movieLibraryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieLibraryButtonActionPerformed
        showMovieList.setListData(movieTitleal.toArray(new String[movieTitleal.size()]));
        if(!movieTitleal.isEmpty())
		{		
            titleTextField.setText(movieTitleal.get(0));
			genreList.setListData(genrehm.get(titleTextField.getText()).replace(", ", "\n").split("[\\r\\n]+"));
			castList.setListData(casthm.get(titleTextField.getText()).replace(", ", "\n").split("[\\r\\n]+"));                
            String selValue = titleTextField.getText();
			temp = UrlUtils.getBaseImagePath() + idhm.get(selValue) + ".jpg";
			File f = new File(temp);
			if(f.exists())
			{
				 imageLabel.setIcon(new ImageIcon(temp));
			}else
			{
			   UrlUtils.saveImgByPosterPath(idhm.get(selValue), posterPathhm.get(selValue));
			   imageLabel.setIcon(new ImageIcon(temp));
		    }
        }
        CardLayout cl = (CardLayout)(basePanel.getLayout());
        cl.show(basePanel, "card4");
    }//GEN-LAST:event_movieLibraryButtonActionPerformed

    private void showMovieListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_showMovieListValueChanged
        if(showMovieList.getSelectedIndex() != -1)
        {
			
            String selValue = showMovieList.getSelectedValue();
			titleTextField.setText(selValue);
			temp = UrlUtils.getBaseImagePath() + idhm.get(selValue) + ".jpg";
			File f = new File(temp);
			if(f.exists())
			{
				imageLabel.setIcon(new ImageIcon(temp));
			}else
			{
				UrlUtils.saveImgByPosterPath(idhm.get(selValue), posterPathhm.get(selValue));
				imageLabel.setIcon(new ImageIcon(temp));
			}
		   temp = casthm.get(selValue).replace(", ", "\n");
		   castList.setListData(temp.split("[\\r\\n]+"));
		   temp = genrehm.get(selValue).replace(", ", "\n");
		   genreList.setListData(temp.split("[\\r\\n]+"));
		   overviewTextArea.setText(overviewhm.get(selValue));
        }
    }//GEN-LAST:event_showMovieListValueChanged

    private void homeLibraryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeLibraryButtonActionPerformed
        CardLayout cl = (CardLayout)(basePanel.getLayout());
        cl.show(basePanel, "card2");
    }//GEN-LAST:event_homeLibraryButtonActionPerformed
	
    private void updateLibraryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateLibraryButtonActionPerformed
        String[] strings = DbUtils.getLibraryFolders();
        ArrayList <String> foldersPresent = new ArrayList<>(); 
		ArrayList <String> moviesInDb = new ArrayList<>(); 
        for(String s: strings)
		{
            File f = new File(s);
            if(f.exists())
            {
				foldersPresent.clear();
				moviesInDb.clear();
                foldersPresent.addAll(FileUtils.getMovieNames(s));
				moviesInDb.addAll(DbUtils.getFolderNamesByFolderPath(s));
				Collections.sort(foldersPresent);
				Collections.sort(moviesInDb);
                if(moviesInDb.size() < foldersPresent.size())
                {
					foldersPresent.removeAll(moviesInDb);
					for(Object o : foldersPresent)
					{                                  
						HashMap<String,String> hm = UrlUtils.getMovieDetailsByName(o.toString());
						if(hm != null)
						{
						    hm.put("FolderBasePath", s);			   
							DbUtils.storeInMovieDb(hm);
							System.out.println("Adding " + o.toString());
						}
					}											
				}else if(moviesInDb.size() > foldersPresent.size())					
					{        
						moviesInDb.removeAll(foldersPresent);
						for(Object o : moviesInDb)
						{
							System.out.println("Deleting " + o.toString());
							DbUtils.deleteMoviesByFolderName(o.toString());
						}                
					}							
            }
        }    		
        allMoviesal = DbUtils.getMovieDetails();
        getMovieData(); 
        JOptionPane.showMessageDialog(null, "The Library is Updated");
    }//GEN-LAST:event_updateLibraryButtonActionPerformed

    private void castListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_castListValueChanged
        if(castList.getSelectedIndex() != -1)
		{
			temp =  castList.getSelectedValue();
			if(!(Objects.equals(temp, "") || Objects.equals(temp, null)))
			{
				castBasedDetails.clear();
				castBasedDetails = DbUtils.getCastBasedMovieDetails(temp);
				Collections.sort(castBasedDetails);
				showMovieList.setListData(castBasedDetails.toArray(new String[castBasedDetails.size()]));
			}       
        }
    }//GEN-LAST:event_castListValueChanged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        movieLibraryButton.doClick();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void genreListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_genreListValueChanged
        if(genreList.getSelectedIndex() != -1)
		{
            temp =  genreList.getSelectedValue();
			if(!(Objects.equals(temp, "") || Objects.equals(temp, null)))
			{
				genreBasedDetails.clear();
				genreBasedDetails = DbUtils.getGenreBasedMovieDetails(temp);
				Collections.sort(genreBasedDetails);
				showMovieList.setListData(genreBasedDetails.toArray(new String[genreBasedDetails.size()]));
			}          
        }

    }//GEN-LAST:event_genreListValueChanged
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIDemo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton BrowseButton;
    private javax.swing.JTextField BrowseTextField;
    private javax.swing.JButton OKButton;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JButton addFoldersButton;
    private javax.swing.JPanel addFoldersPanel;
    private javax.swing.JList<String> addedFoldersList;
    private javax.swing.JScrollPane addedFoldersScrollPane;
    private javax.swing.JPanel basePanel;
    private javax.swing.JLabel castLabel;
    private javax.swing.JList<String> castList;
    private javax.swing.JScrollPane castScrollPane;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JList<String> genreList;
    private javax.swing.JScrollPane genreScrollPane;
    private javax.swing.JButton homeLibraryButton;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton movieLibraryButton;
    private javax.swing.JPanel movieLibraryPanel;
    private javax.swing.JScrollPane movieTitleScrollPane;
    private javax.swing.JScrollPane overviewScrollPane;
    private javax.swing.JTextArea overviewTextArea;
    private javax.swing.JButton resetButton;
    private javax.swing.JList<String> showMovieList;
    private javax.swing.JPanel startPanel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JButton updateLibraryButton;
    // End of variables declaration//GEN-END:variables
}