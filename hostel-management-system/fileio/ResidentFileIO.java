package fileio;

import entity.Resident;

import java.io.*;

public class ResidentFileIO {

    private static final String FILE_NAME = "residents.txt";

    private static final String TEMP_FILE = "temp.txt";

    public static void createFileIfNotExists() throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists())
            file.createNewFile();
    }

    public static boolean idExists(String id) {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                Resident s = Resident.fromLine(line);

                if (s != null && s.getId().equals(id))
                    return true;
            }

        } catch (IOException ignored) {
        }

        return false;
    }

    public static int countRecords() {

        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (Resident.fromLine(line) != null)
                    count++;
            }

        } catch (IOException ignored) {
        }

        return count;
    }

    public static void addResident(Resident s) throws IOException {

        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(FILE_NAME, true)))) {

            pw.println(s.toLine());
        }
    }

    public static boolean updateResident(Resident s) throws IOException {

        File inputFile = new File(FILE_NAME);
        File tempFile = new File(TEMP_FILE);

        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));

                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;

            while ((line = br.readLine()) != null) {

                Resident existing = Resident.fromLine(line);

                if (existing != null &&
                        existing.getId().equals(s.getId())) {

                    bw.write(s.toLine());
                    found = true;

                } else {

                    bw.write(line);
                }

                bw.newLine();
            }
        }

        if (found) {

            if (!inputFile.delete() ||
                    !tempFile.renameTo(inputFile)) {

                throw new IOException("Could not finalize update.");
            }

        } else {

            tempFile.delete();
        }

        return found;
    }

    public static boolean deleteResident(String id) throws IOException {

        File inputFile = new File(FILE_NAME);
        File tempFile = new File(TEMP_FILE);

        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));

                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;

            while ((line = br.readLine()) != null) {

                Resident existing = Resident.fromLine(line);

                if (existing != null &&
                        existing.getId().equals(id)) {

                    found = true;
                    continue;
                }

                bw.write(line);
                bw.newLine();
            }
        }

        if (found) {

            if (!inputFile.delete() ||
                    !tempFile.renameTo(inputFile)) {

                throw new IOException("Could not finalize delete.");
            }

        } else {

            tempFile.delete();
        }

        return found;
    }

    public static Object[][] getAllResident() {

        int total = countRecords();

        Object[][] rows = new Object[total][4];

        int idx = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null &&
                    idx < total) {

                Resident s = Resident.fromLine(line);

                if (s != null) {

                    Object[] row = s.toRow();

                    rows[idx][0] = row[0];
                    rows[idx][1] = row[1];
                    rows[idx][2] = row[2];
                    rows[idx][3] = row[3];

                    idx++;
                }
            }

        } catch (IOException ignored) {
        }

        return rows;
    }

    public static Object[][] searchResident(String keyword) {

        String kw = keyword.toLowerCase();

        // Pass 1 - Count matches
        int matchCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                Resident s = Resident.fromLine(line);

                if (s != null &&
                        (s.getId().toLowerCase().contains(kw)
                                || s.getName().toLowerCase().contains(kw))) {

                    matchCount++;
                }
            }

        } catch (IOException ignored) {
        }

        // Pass 2 - Store matches
        Object[][] results = new Object[matchCount][4];

        int idx = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null &&
                    idx < matchCount) {

                Resident s = Resident.fromLine(line);

                if (s != null &&
                        (s.getId().toLowerCase().contains(kw)
                                || s.getName().toLowerCase().contains(kw))) {

                    Object[] row = s.toRow();

                    results[idx][0] = row[0];
                    results[idx][1] = row[1];
                    results[idx][2] = row[2];
                    results[idx][3] = row[3];

                    idx++;
                }
            }

        } catch (IOException ignored) {
        }

        return results;
    }
}