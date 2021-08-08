import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answerList = new ArrayList<>();
        Map<String, Genre> genrePlayCountMap = new HashMap<>();
        Map<String, PriorityQueue<Song>> genreSongMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            Genre genre = genrePlayCountMap.getOrDefault(genres[i], new Genre(genres[i], 0));
            genre.countOfPlay += plays[i];
            genrePlayCountMap.put(genres[i], genre);

            PriorityQueue<Song> songList = genreSongMap.getOrDefault(genres[i], new PriorityQueue<>(new SongComparator()));
            songList.add(new Song(i, plays[i]));
            genreSongMap.put(genres[i], songList);
        }

        List<Genre> bestGenreList = genrePlayCountMap.values().stream().sorted(Comparator.comparing(Genre::getCountOfPlay).reversed()).collect(Collectors.toList());

        for (Genre genre : bestGenreList) {
            PriorityQueue<Song> songPriorityQueue = genreSongMap.get(genre.name);

            int countOfSong = 0;
            while (!songPriorityQueue.isEmpty() && countOfSong < 2) {
                countOfSong++;
                Song bestSong = songPriorityQueue.poll();
                answerList.add(bestSong.id);
            }
        }

        int[] answer = new int[answerList.size()];
        int index = 0;
        for (int numOfSong : answerList) {
            answer[index] = numOfSong;
            index++;
        }

        return answer;
    }
    
    private static class Song {
        private int id;
        private int countOfPlay;

        public Song(int id, int countOfPlay) {
            this.id = id;
            this.countOfPlay = countOfPlay;
        }
    }

    private static class Genre {
        private String name;
        private long countOfPlay;

        public Genre(String name, long countOfPlay) {
            this.name = name;
            this.countOfPlay = countOfPlay;
        }

        public long getCountOfPlay() {
            return countOfPlay;
        }
    }

    private static class SongComparator implements Comparator<Song> {
        @Override
        public int compare(Song s1, Song s2) {
            if (s1.countOfPlay == s2.countOfPlay) {
                return s1.id - s2.id;
            }
            return s2.countOfPlay - s1.countOfPlay;
        }
    }
}