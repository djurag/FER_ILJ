# Zadatak za 3 laboratorijsku vjezbu, ILJ
# autor Darko Juraga
import metacomm.combinatorics.all_pairs2

all_pairs = metacomm.combinatorics.all_pairs2.all_pairs2

task1 = [["MS_XP", "MS_DOS33", "MS_78.*", "MS_10", "Mac_Puma", "Mac_Chee", "Mac_Jagu", "Deb_Jessie", "Deb_Wheezy",
          "Ubuntu_10*"],
         ["Gcc_4.9.3", "gcc_47*", "g++_4*", "PHP_5", "PHP_4", "python_36", "python_35", "Java_8", "Java6_121",
          "Java_7*"],
         ["Ffox_a7", "Ffox_a71", "Mozilla_17", "Lynx_*", "IE_bbc", "IE_a", "IE_b", "IE_d", "Opera_a", "Opera_b"],
         ["junit_45", "junit_48", "junit_482", "libxml_291", "libxsl_t28", "xmlpath_1a", "xmlpath_1b", "spin_64",
          "jpf_8", "jpf_7"],
         ["sqlite3_a", "sqlite3_b", "Sybase_*", "mySQL_a", "mySQL_b", "mySQL_c", "progress_1", "Progress_2", "Oracle_a",
          "Oracle_b"],
         ["IPv4_a", "IPv4_r", "IPv4_t", "IPv4_t1", "IPv6_b", "IPv6_c", "IPv4_we", "IPv4_11", "IPv4_d2", "IPv6_g"],
         ["AdobeR_a", "AdobeR_b", "AdobeR_c", "acrobat_1", "acrobat_2", "acrobat_3", "zathura_*", "okular_*", "xpdf_a",
          "xpdf_b"]]

task2 = [["MS_XP", "MS_DOS33", "MS_78.*", "MS_10", "Mac_Puma", "Mac_Chee", "Mac_Jagu", "Deb_Jessie", "Deb_Wheezy",
          "Ubuntu_10*"],
         ["Gcc_4.9.3", "gcc_47*", "g++_4*", "PHP_5", "PHP_4", "python_36", "python_35", "Java_8", "Java6_121",
          "Java_7*"],
         ["Ffox_a7", "Ffox_a71", "Mozilla_17", "Lynx_*", "IE_bbc", "IE_a", "IE_b", "IE_d", "Opera_a", "Opera_b"],
         ["junit_45", "junit_48", "junit_482", "libxml_291", "libxsl_t28", "xmlpath_1a", "xmlpath_1b", "spin_64",
          "jpf_8", "jpf_7"],
         ["sqlite3_a", "sqlite3_b", "Sybase_*", "mySQL_a", "mySQL_b", "mySQL_c", "progress_1", "Progress_2", "Oracle_a",
          "Oracle_b"],
         ["AdobeR_a", "AdobeR_b", "AdobeR_c", "acrobat_1", "acrobat_2", "acrobat_3", "zathura_*", "okular_*", "xpdf_a",
          "xpdf_b"]]

task3 = [["MS_XP", "MS_78.*", "MS_10", "Mac_Puma", "Mac_Chee", "Mac_Jagu", "Deb_Jessie", "Deb_Wheezy",
          "Ubuntu_10*"],
         ["Gcc_4.9.3", "gcc_47*", "g++_4*", "PHP_5", "PHP_4", "python_36", "python_35", "Java_8", "Java6_121",
          "Java_7*"],
         ["Ffox_a7", "Ffox_a71", "Mozilla_17", "IE_bbc", "IE_a", "IE_b", "IE_d", "Opera_a", "Opera_b"],
         ["junit_45", "junit_48", "junit_482", "libxml_291", "libxsl_t28", "xmlpath_1a", "xmlpath_1b", "spin_64",
          "jpf_8", "jpf_7"],
         ["sqlite3_a", "sqlite3_b", "Sybase_*", "mySQL_a", "mySQL_b", "mySQL_c", "progress_1", "Progress_2", "Oracle_a",
          "Oracle_b"],
         ["IPv4_a", "IPv4_r", "IPv4_t", "IPv4_t1", "IPv6_b", "IPv6_c", "IPv4_we", "IPv4_11", "IPv4_d2", "IPv6_g"],
         ["AdobeR_a", "AdobeR_b", "AdobeR_c", "acrobat_1", "acrobat_2", "acrobat_3", "zathura_*", "okular_*", "xpdf_a",
          "xpdf_b"]]

task4 = [["MS", "Mac", "Deb", "Ubuntu"],
         ["gcc", "g++", "PHP", "python", "Java"],
         ["Ffox", "Mozilla", "Lynx", "IE", "Opera"],
         ["junit", "libxml", "libxsl", "xmlpath", "spin", "jpf"],
         ["sqlite", "Sybase", "mySQL", "progress", "Oracle"],
         ["IPv4", "IPv6"],
         ["AdobeR", "acrobat", "zathura", "okular", "xpdf"]]

print "\nTask 1:"
pairwise = all_pairs(task1, n=2)
for i, v in enumerate(pairwise):
    print "%i:\t%s" % (i, str(v))

print "\nTask 2:"
pairwise = all_pairs(task2, n=2)
for i, v in enumerate(pairwise):
    print "%i:\t%s" % (i, str(v))

print "\nTask 3:"
pairwise = all_pairs(task3, n=2)
for i, v in enumerate(pairwise):
    print "%i:\t%s" % (i, str(v))

print "\nTask 4:"
pairwise = all_pairs(task4, n=2)
for i, v in enumerate(pairwise):
    print "%i:\t%s" % (i, str(v))
