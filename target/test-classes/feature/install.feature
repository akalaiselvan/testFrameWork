Feature: Setup Environment

#  Scenario Outline: install setup
#    Given Clear Registry "<regname>" and "<type>"
#    When Start Web Installer and switch to labtest
#    Then Enter Username and Password
#    Examples:
#      |regname|type|
#      |localreg|0|
#      |EnvSett|1|
#      |restReq|false|



    Scenario: try dataTables
      Given Enter the data in Table
            |itemname|Tax|
            |rice    |5  |
            |ricebag |45 |


#  Scenario: try list dataTables
#    Given Enter the data in Table using List
#      |rice    |5  |
#      |ricebag |45 |