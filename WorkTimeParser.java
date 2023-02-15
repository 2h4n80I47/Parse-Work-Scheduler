import java.util.*;

class WorkTimeParser {

	public String[][] prepareWorkTime(String[][] grafic) {
		// Структура для хранения рабочего времени каждого специалиста
		Map<String, List<String[]>> specialistTime = new HashMap<>();

		// Обрабатываем каждую запись в расписании
		for (String[] row : grafic) {
		    String specialistName = row[0];
		    if (!specialistTime.containsKey(specialistName)) {
			specialistTime.put(specialistName, new ArrayList<>());
		    }

		    List<String[]> specialistSchedule = specialistTime.get(specialistName);
		    String[] workHours = Arrays.copyOfRange(row, 1, 8);

		    // Разбиваем время на диапазоны по дням недели
		    for (int i = 0; i < 7; i++) {
			String[] timeRange = workHours[i].split("-");
			if (timeRange.length == 2) {
			    specialistSchedule.add(new String[]{Integer.toString(i), timeRange[0], timeRange[1]});
			}
		    }
		}

		// Создаем выходной массив и заполняем его обработанными записями
		String[][] output = new String[specialistTime.size()][8];
		int i = 0;
		for (String specialist : specialistTime.keySet()) {
		    // Добавляем ФИО специалиста в первый столбец выходного массива
		    output[i][0] = specialist;

		    // Объединяем диапазоны
		    List<String[]> specialistSchedule = specialistTime.get(specialist);
		    Collections.sort(specialistSchedule, Comparator.comparingInt(a -> Integer.parseInt(a[0])));
		    for (int j = 0; j < 7; j++) {
			List<String> ranges = new ArrayList<>();
			for (String[] range : specialistSchedule) {
			    if (range[0].equals(Integer.toString(j))) {
				ranges.add(range[1] + "-" + range[2]);
			    }
			}
			String mergedRange = mergeRanges(ranges);
			output[i][j+1] = mergedRange;
		    }
		    i++;
		}

		return output;
	}
	private String mergeRanges(List<String> ranges) {
		if (ranges.isEmpty()) {
			return "";
		}
		Collections.sort(ranges);

		List<String> mergedRanges = new ArrayList<>();
		String[] currentRange = ranges.get(0).split("-");
		for (int i = 1; i < ranges.size(); i++) {
			String[] nextRange = ranges.get(i).split("-");
			if (nextRange[0].compareTo(currentRange[1]) <= 0) {
			    // Следующий диапазон перекрывается с текущим диапазоном
			    currentRange[1] = nextRange[1];
			} else {
			    // Следующий диапазон не пересекается с текущим диапазоном
			    mergedRanges.add(currentRange[0] + "-" + currentRange[1]);
			    currentRange = nextRange;
			}
		}
		mergedRanges.add(currentRange[0] + "-" + currentRange[1]);

		return String.join(",", mergedRanges);
	}
}
class Ideone{
	public static void main(String[] args) {
		String[][] arr = new String[][] {
			{"ИВАНОВ ИВАН ИВАНОВИЧ", "12:00-16:00", "12:00-16:00", "12:00-16:00", "12:00-16:00", "12:00-16:00", "12:00-16:00", ""},
			{"ИВАНОВ ИВАН ИВАНОВИЧ", "08:00-12:00", "08:00-10:00", "08:00-16:00", "", "", "", ""},
			{"ПЕТРОВ ПЕТР ПЕТРОВИЧ", "12:00-16:00", "", "12:00-16:00", "", "12:00-16:00", "", ""},
			// ...
		};

		WorkTimeParser parser = new WorkTimeParser();
		String[][] result = parser.prepareWorkTime(arr);

		for (String[] row : result) {
			System.out.println(Arrays.toString(row));
		}
	}
}

