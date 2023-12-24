
import { nbaService } from "@/services/SportsService";

export default async function NBAStanding() {
    let error = false;
    let conferenceStandings;

    try {
        conferenceStandings = await nbaService.getStandings();
    } catch (err) {
        error = true;
    }

    return (
        <div>
            <h2 className="font-bold">NBA</h2>
            
            <div className="flex flex-wrap sm:flex-nowrap gap-2">
                {conferenceStandings?.data.standingGroups.map((conference) => (
                    <table key={conference.conference} className="w-full text-sm table-auto border-collapse border border-slate-500 text-center">
                        <caption className="p-2 caption-top text-left bg-[#006bb6] text-white font-bold text-md">
                            {conference.conference}
                        </caption>
                        <thead>
                            <tr className="bg-[#032f4f] text-white">
                                <th className="p-2 bg-[#006bb6] border border-slate-600">Rank</th>
                                <th className="p-2 border border-slate-600">Image</th>
                                <th className="p-2 border border-slate-600">Name</th>
                                <th className="p-2 border border-slate-600">Wins</th>
                                <th className="p-2 border border-slate-600">Losses</th>
                            </tr>
                        </thead>
                        <tbody>
                            {conference.teams.map((team) => (
                                <tr key={team.profile.id} className="even:bg-gray-100">
                                    <td className="border border-slate-700">{team.standings.confRank}</td>
                                    <td className="border border-slate-700"><img width="30" height="30" src={`https://cdn.nba.com/logos/nba/${team.profile.id}/primary/L/logo.svg`} alt="team logo"/></td>
                                    <td className="border border-slate-700">{team.profile.city} {team.profile.name}</td>
                                    <td className="border border-slate-700">{team.standings.wins}</td>
                                    <td className="border border-slate-700">{team.standings.losses}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ))}
            </div>
        </div>
    );
}