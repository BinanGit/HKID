@GenerateHKID_Verify

Feature: HKID Checking

#  wrong = XC356184(9), correct = XC356184(7)
#  wrong = E445520(ZZ), correct = E445520(0)

  Scenario: Generate a HKID and check if it is valid
    Given The user generate a HKID
    Then The HKID generated should be verified

#  @TestHKID
#  Scenario: Verify if a HKID provided is valid
##    Given The user provide a HKID of "ZZ112346(2)" with a check digit
##    Given The user provide a HKID of "MC182848A" with a check digit
##    Given The user provide a HKID of "E445520(ZZ)" with a check digit
#    Given The user provide a HKID of "C9017311" with a check digit
#    When The user input the HKID into the checking formula
#    Then The user shall verify if the HKID is valid